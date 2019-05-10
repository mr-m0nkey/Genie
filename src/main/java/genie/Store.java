/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genie;

import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author mayowa
 */
@Component
public class Store {


    private ExecutorService singleThreadExecutorService;
    private ExecutorService parallelThreadExecutorService;

    private Store() {
        singleThreadExecutorService = Executors.newSingleThreadExecutor();
        parallelThreadExecutorService = Executors.newFixedThreadPool(3);
    }

    
    public ExecutorService getTunnelThread() {
        return singleThreadExecutorService;
    }

    public ExecutorService getThreadPool() { return parallelThreadExecutorService; }
    
}
