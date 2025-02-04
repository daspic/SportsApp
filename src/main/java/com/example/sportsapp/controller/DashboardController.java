package com.example.sportsapp.controller;

import com.example.sportsapp.model.Player;
import com.example.sportsapp.model.Team;
import com.example.sportsapp.model.User;
import com.example.sportsapp.repository.PlayersRepository;
import com.example.sportsapp.repository.TeamsRepository;
import com.example.sportsapp.repository.UserRepository;
import com.example.sportsapp.service.PlayerService;
import com.example.sportsapp.service.TeamsService;
import com.example.sportsapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class DashboardController {

    private final UserService userService;
    private final TeamsService teamService;
    private final PlayerService playerService;
    private final UserRepository userRepository;
    private final TeamsRepository teamsRepository;
    private final PlayersRepository playersRepository;


    private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);

    @Autowired
    public DashboardController(UserService userService, TeamsService teamService, PlayerService playerService, UserRepository userRepository, TeamsRepository teamsRepository, PlayersRepository playersRepository) {
        this.userService = userService;
        this.teamService = teamService;
        this.playerService = playerService;
        this.userRepository = userRepository;
        this.teamsRepository = teamsRepository;
        this.playersRepository = playersRepository;
    }

    @GetMapping("/dashboard")
    public String showDashboard(
            Model model,
            @RequestParam(value = "userSort", required = false, defaultValue = "id") String userSort,
            @RequestParam(value = "userPage", defaultValue = "0") int userPage,
            @RequestParam(value = "userSize", defaultValue = "10") int userSize,
            @RequestParam(value = "teamSort", required = false, defaultValue = "id") String teamSort,
            @RequestParam(value = "teamPage", defaultValue = "0") int teamPage,
            @RequestParam(value = "teamSize", defaultValue = "10") int teamSize,
            @RequestParam(value = "playerSort", required = false, defaultValue = "id") String playerSort,
            @RequestParam(value = "playerPage", defaultValue = "0") int playerPage,
            @RequestParam(value = "playerSize", defaultValue = "10") int playerSize
    )
    {
        prepareDashboardModel(model, userSort, userPage, userSize, teamSort, teamPage, teamSize, playerSort, playerPage, playerSize);
        return "dashboard";
    }

    private void prepareDashboardModel(Model model, String userSort, int userPage, int userSize, String teamSort, int teamPage, int teamSize, String playerSort, int playerPage, int playerSize) {
        // Fetch paginated data
        Pageable userPageable = PageRequest.of(userPage, userSize, Sort.by(userSort));
        Page<User> userPageData = userService.getAllUsersPaginated(userPageable);

        if (userPageData == null) {
            System.out.println("userPageData is null");
        } else {
            System.out.println("userPageData: " + userPageData);
        }

        Pageable teamPageable = PageRequest.of(teamPage, teamSize, Sort.by(teamSort));
        Page<Team> teamPageData = teamService.getAllTeamsPaginated(teamPageable);

        model.addAttribute("newTeam", new Team()); // Provide an empty team for form

        // Validate sort fields
        Pageable playerPageable = PageRequest.of(playerPage, playerSize, Sort.by(playerSort));
        Page<Player> playerPageData = playerService.getAllPlayersPaginated(playerPageable);

        // Add to model
        model.addAttribute("userPageData", userPageData);
        model.addAttribute("teamPageData", teamPageData);
        model.addAttribute("playerPageData", playerPageData);

        // Current pages and sizes
        model.addAttribute("userPage", userPage);
        model.addAttribute("userSize", userSize);
        model.addAttribute("userSort", userSort);

        model.addAttribute("teamPage", teamPage);
        model.addAttribute("teamSize", teamSize);
        model.addAttribute("teamSort", teamSort);

        model.addAttribute("playerPage", playerPage);
        model.addAttribute("playerSize", playerSize);
        model.addAttribute("playerSort", playerSort);
    }

    @GetMapping("/users")
    public String showUsersDashboard(
            Model model,
            @RequestParam(value = "page", defaultValue = "0") int page, // Current page (default: 0)
            @RequestParam(value = "size", defaultValue = "10") int size, // Users per page (default: 10)
            @RequestParam(value = "userSort", required = false) String userSort) {

        if (userSort == null || userSort.isEmpty()) userSort = "id";

        Pageable pageable = PageRequest.of(page, size, Sort.by(userSort));
        Page<User> userPage = userService.getAllUsersSortedPaginated(pageable);

        model.addAttribute("userPage", userPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("userSort", userSort);

        return "Users"; // Render the users.html template
    }

    @GetMapping("/teams")
    public String showTeamsDashboard(
            Model model,
            @RequestParam(value = "size", defaultValue = "10") int pageSize,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "teamSort", required = false) String teamSort){

        if (teamSort == null || teamSort.isEmpty()) teamSort = "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(teamSort)); // Create Pageable object
        Page<Team> teamPage = teamService.getAllTeamsPaginated(pageable); // Use Pageable in your service method

        model.addAttribute("teamPage", teamPage);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("currentPage", page);
        model.addAttribute("teamSort", teamSort);

        return "teams"; // Return the name of your Thymeleaf template
    }

    @GetMapping("/players")
    public String showPlayersDashboard(
            Model model,
            @RequestParam(value = "size", defaultValue = "10") int pageSize,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "playerSort", required = false) String playerSort){

        if (playerSort == null || playerSort.isEmpty()) playerSort = "id";

        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(playerSort));
        Page<Player> playerPage = playerService.getAllPlayersPaginated(pageable); // Use Pageable in your service

        model.addAttribute("playerPage", playerPage);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("currentPage", page);
        model.addAttribute("playerSort", playerSort);

        return "players";
    }

    @PostMapping("/dashboard/addPlayer")
    public String addUserAsPlayer(
            @RequestParam("userEmail") String userEmail,
            @RequestParam("teamName") String teamName,
            @RequestParam("playerStats") String playerStats,
            @RequestParam(value = "userSort", required = false, defaultValue = "id") String userSort,
            @RequestParam(value = "userPage", defaultValue = "0") int userPage,
            @RequestParam(value = "userSize", defaultValue = "10") int userSize,
            @RequestParam(value = "teamSort", required = false, defaultValue = "id") String teamSort,
            @RequestParam(value = "teamPage", defaultValue = "0") int teamPage,
            @RequestParam(value = "teamSize", defaultValue = "10") int teamSize,
            @RequestParam(value = "playerSort", required = false, defaultValue = "id") String playerSort,
            @RequestParam(value = "playerPage", defaultValue = "0") int playerPage,
            @RequestParam(value = "playerSize", defaultValue = "10") int playerSize,
            Model model) {
        prepareDashboardModel(model, userSort, userPage, userSize, teamSort, teamPage, teamSize, playerSort, playerPage, playerSize);


        // Find the user by email
        Optional<User> optionalUser = userService.findByEmail(userEmail);
        if (optionalUser.isEmpty()) {
            model.addAttribute("errorMessage", "User not found.");
            return "dashboard";
        }

        User existingUser = optionalUser.get();

        // Check if the user has the "player" role
        if (!existingUser.getRole().equalsIgnoreCase("PLAYER")) {
            model.addAttribute("errorMessage", "Error: User '" + userEmail + "' must have the 'PLAYER' role to be added.");
            return "dashboard";
        }

        Optional<Team> optionalTeam = teamService.findByNameIgnoreCase(teamName);
        if (optionalTeam.isEmpty()) {
            model.addAttribute("errorMessage", "Error: Team with name '" + teamName + "' was not found.");
            return "dashboard";
        }
        Team team = optionalTeam.get();
        User user = optionalUser.get();

        // Check if the player already exists in the team
        boolean playerExists = playersRepository.existsByUserAndTeam(user, team);
        if (playerExists) {
            model.addAttribute("errorMessage", "Error: Player is already in team '" + teamName.substring(0, 1).toUpperCase() + teamName.substring(1).toLowerCase() + "'.");
            return "dashboard";
        }

//        // Determine the next available player ID within the team
//        int nextPlayerNumber = playersRepository.findMaxTeamPlayerNumberByTeam(team) + 1;

        // Create a new Player entity
        Player player = new Player();
//        player.setTeamPlayerNumber(nextPlayerNumber); // Assign new number within the team
        player.setUser(existingUser);
        player.setTeam(team);
        player.setPlayerStats(playerStats);

        playerService.save(player);

        // Fetch paginated data (only for user data)
        Pageable userPageable = PageRequest.of(0, 10, Sort.by("id"));
        Page<User> userPageData = userService.getAllUsersPaginated(userPageable);
        model.addAttribute("userPageData", userPageData);

        return "redirect:/dashboard";
    }
}
