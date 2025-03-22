package com.example.sportsapp.controller;

import com.example.sportsapp.model.Player;
import com.example.sportsapp.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Controller
@RequestMapping("/players")
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    private static final String UPLOAD_DIR = "uploads/";

    @PostMapping("/{playerId}/upload")
    public ResponseEntity<String> uploadProfilePicture(
            @PathVariable Long playerId,
            @RequestParam("image") MultipartFile file) {

        try {
            // Ensure the upload directory exists
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Generate a unique filename
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);

            // Save the file to the server
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Save the file path in the database (relative path)
            String filePathString = "images/uploads/" + fileName;
            playerService.updateProfilePicture(playerId, filePathString);

            return ResponseEntity.ok(filePathString); // ✅ Send correct path

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Could not upload file: " + e.getMessage());
        }
    }


    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/{id}")
    public String getPlayerById(@PathVariable Long id, Model model) {
        Player player = playerService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Player not found with ID: " + id));
        model.addAttribute("player", player);
        return "playerDetails";
    }

    @PostMapping("/addPlayer")
    public String addUserAsPlayer(
            @RequestParam("userEmail") String userEmail,
            @RequestParam("teamName") String teamName,
            @RequestParam("playerStats") String playerStats,
            @RequestParam(value = "playerSort", required = false, defaultValue = "id") String playerSort,
            @RequestParam(value = "page", defaultValue = "0") int playerPage,
            @RequestParam(value = "size", defaultValue = "10") int playerSize,
            RedirectAttributes redirectAttributes) {

        // Call service method
        String errorMessage = playerService.addUserAsPlayer(userEmail, teamName, playerStats);
        if (errorMessage.contains("Error")) {
            redirectAttributes.addFlashAttribute("errorAddPlayerToTeam", errorMessage);
        } else {
            redirectAttributes.addFlashAttribute("successAddPlayerToTeam", errorMessage);
        }

        return "redirect:/players"
                + "?page=" + playerPage + "&size=" + playerSize + "&playerSort=" + playerSort;
    }
}
