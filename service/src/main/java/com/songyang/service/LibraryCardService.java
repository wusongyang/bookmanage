package com.songyang.service;

import com.songyang.common.StandardResponse;
import com.songyang.pojo.LibraryCard;

public interface LibraryCardService {
    StandardResponse addLibraryCard(LibraryCard libraryCard);
    StandardResponse deleteLibraryCard(int id);
    StandardResponse selectLibraryCardByUserId(int userId);

}
