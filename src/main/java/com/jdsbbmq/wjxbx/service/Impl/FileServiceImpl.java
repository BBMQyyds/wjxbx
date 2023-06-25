package com.jdsbbmq.wjxbx.service.Impl;

import com.google.gson.Gson;
import com.jdsbbmq.wjxbx.bean.file.File;
import com.jdsbbmq.wjxbx.dao.AttributionEntityMapper;
import com.jdsbbmq.wjxbx.dao.FileEntityMapper;
import com.jdsbbmq.wjxbx.dao.entity.AttributionEntity;
import com.jdsbbmq.wjxbx.dao.entity.FileEntity;
import com.jdsbbmq.wjxbx.service.FileService;
import jakarta.annotation.Resource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

@Service
public class FileServiceImpl implements FileService {
    @Resource
    private FileEntityMapper fileEntityMapper;

    @Resource
    private AttributionEntityMapper attributionEntityMapper;


    /*
        增删改
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    @Async("asyncServiceExecutor")
    public CompletableFuture<Integer> insertFile(File file) {
        try {
            FileEntity fileEntity = new FileEntity(file);
            AttributionEntity attributionEntity = new AttributionEntity(file.getParentId(), file.getId(), "file", new Gson().toJson(file));
            int a = fileEntityMapper.insertFile(fileEntity);
            int b = attributionEntityMapper.insertAttribution(attributionEntity);
            if (a == 0 || b == 0) {
                throw new RuntimeException("Failed to insert file"); // 触发回滚
            }
            return CompletableFuture.completedFuture(1); // 插入成功
        } catch (Exception e) {
            // 处理异常
            throw new RuntimeException("Failed to insert file", e); // 触发回滚
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    @Async("asyncServiceExecutor")
    public CompletableFuture<Integer> insertCopyFile(File file) {
        try {
            FileEntity fileEntity = new FileEntity(file);
            AttributionEntity attributionEntity = new AttributionEntity(file.getParentId(), file.getId(), "file", new Gson().toJson(file));
            int a = fileEntityMapper.insertFile(fileEntity);
            int b = attributionEntityMapper.insertCopyAttribution(attributionEntity);
            if (a == 0 || b == 0) {
                throw new RuntimeException("Failed to copy file"); // 触发回滚
            }
            return CompletableFuture.completedFuture(1); // 插入成功
        } catch (Exception e) {
            // 处理异常
            throw new RuntimeException("Failed to copy file", e); // 触发回滚
        }
    }

    @Override
    @Async("asyncServiceExecutor")
    public CompletableFuture<Integer> updateFile(File file) {
        return CompletableFuture.completedFuture(fileEntityMapper.updateFile(new FileEntity(file)));
    }

    @Override
    @Async("asyncServiceExecutor")
    public CompletableFuture<Integer> deleteFile(String fileId) {
        return CompletableFuture.completedFuture(fileEntityMapper.deleteFile(fileId));
    }


}
