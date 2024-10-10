package vn.atstar.service;

import java.util.List;

import vn.atstar.entity.Video;

public interface IVideoService {
    void insert(Video video);

    void update(Video video);

    void delete(String videoId) throws Exception;

    Video findById(String videoId);

    List<Video> findByTitle(String title);

    List<Video> findAll();

    List<Video> findAll(int page, int pagesize);

    int count();
}