package com.example.Backend.controller;

import com.example.Backend.DAO.Pet.PetDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/pet")
public class ImageController {

    @Autowired
    private PetDAO petDAO;
    @GetMapping("/img")
    public ResponseEntity<byte[]> getRoomImage(@RequestParam("id") int petID) {

        byte[] imageData = petDAO.getImageById(petID);


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
    }
//    @GetMapping("/pdf")
//    public ResponseEntity<byte[]> getHotelImage(@RequestParam("id") UUID imageId) {
//        Optional<HotelImage> optionalImage = hotelImageRepository.findById(imageId);
//
//        if (optionalImage.isPresent()) {
//            HotelImage image = optionalImage.get();
//
//             byte[] imageData = image.getSource();
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.IMAGE_JPEG);
//
//            return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
}

