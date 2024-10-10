package vn.atstar.service.impl;

import java.util.List;

import vn.atstar.dao.IVideoDao;
import vn.atstar.dao.impl.VideoDao;
import vn.atstar.entity.Video;
import vn.atstar.service.IVideoService;

public class VideoServiceImpl implements IVideoService {
    private final IVideoDao videoDao = new VideoDao();

    @Override
    public void insert(Video video) {
        videoDao.insert(video);
    }

    @Override
    public void update(Video video) {
        videoDao.update(video);
    }

    @Override
    public void delete(String videoId) throws Exception {
        videoDao.delete(videoId);
    }

    @Override
    public Video findById(String videoId) {
        return videoDao.findById(videoId);
    }

    @Override
    public List<Video> findByTitle(String title) {
        return videoDao.findByTitle(title);
    }

    @Override
    public List<Video> findAll() {
        return videoDao.findAll();
    }

    @Override
    public List<Video> findAll(int page, int pagesize) {
        return videoDao.findAll(page, pagesize);
    }

    @Override
    public int count() {
        return videoDao.count();
    }
}
