package dev.mayaqq.ygasi.secrete;

import java.time.LocalDate;
import java.util.Base64;

import static dev.mayaqq.ygasi.Ygasi.LOGGER;

public class Frog {
    //its a frog, frog in a blender!
    public static void blender() {
        String frog = "ICAgICAgICAgICBfX18KICAgX19fX19fX3xfX198X19fX19fCl9ffF9fX19fX19fX19fX19fX19fX3wKXCAgXV9fX19fX19fX19fX19fX19bIGAtLS0uIAogYC4gICAgICAgICAgICAgICAgICAgX19fICBMCiAgfCAgIF8gICAgICAgICAgICAgIHwgICBMIHwKICB8IC4nX2AtLS5fX18gICBfXyAgfCAgIHwgfCAgICAgIAogIHwoICdvYCAgIC0gLmAuJ18gKSB8ICAgRiBGICAgICAgIAogIHwgYC0uXyAgICAgIGBfYC4vXyB8ICAvIC8gICAgICAgICAKICBKICAgJy9cXCAgICAoIC4nLyApRi4nIC8KICAgTCAsX18vL2AtLS0nYC0nXy9KICAuJwogICBKICAvLScgICAgICAgICcvIEYuJwogICAgTCAgICAgICAgICAgICcgSicKICAgIEogYC5gLS4gLi0nLicgIEYKICAgICBMICBgLi0nLi0nICAgSgogICAgIHxfXyhfXyhfX18pX198CiAgICAgRiAgICAgICAgICAgIEoKICAgIEogICAgICAgICAgICAgIEwKICAgIHxfX19fX19fX19fX19fX3w=";

        if (LocalDate.now().getDayOfWeek().getValue() == 3) {
            byte[] decodedBytes = Base64.getDecoder().decode(frog);
            // Split the bytes into lines using the newline character
            String[] lines = new String(decodedBytes).split("\n");
            // Print each line
            for (String line : lines) {
                LOGGER.info(line);
            }
            if (Math.random() < 0.5) {
                LOGGER.info("It's wednesday my dudes");
            } else {
                LOGGER.info("Blender is the best 3D software.");
            }
        }
    }
}
