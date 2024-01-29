package com.tribal.demo.service;

import com.tribal.demo.Domain.JokeDomain;
import com.tribal.demo.service.interfaces.GetData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Service
public class JokeService {

    private final GetData getData;

    public List<JokeDomain> getAllJokes()throws Exception{
        ArrayList<JokeDomain> jokes = null;
        List<CompletableFuture<JokeDomain>> futures = new ArrayList<>();
        CompletableFuture<JokeDomain> future1 = CompletableFuture.supplyAsync(() -> {return getData.execute();});
        CompletableFuture<JokeDomain> future2 = CompletableFuture.supplyAsync(() -> {return getData.execute();});
        CompletableFuture<JokeDomain> future3 = CompletableFuture.supplyAsync(() -> {return getData.execute();});
        CompletableFuture<JokeDomain> future4 = CompletableFuture.supplyAsync(() -> {return getData.execute();});
        CompletableFuture<JokeDomain> future5 = CompletableFuture.supplyAsync(() -> {return getData.execute();});
        futures.add(future1);
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(future1, future2, future3, future4, future5);
        CompletableFuture<ArrayList<JokeDomain>> combinedFuture = allFutures.thenApply(v -> {
                ArrayList<JokeDomain> jokesAsync = new ArrayList<>();
                jokesAsync.add(future1.join());
                jokesAsync.add(future2.join());
                jokesAsync.add(future3.join());
                jokesAsync.add(future4.join());
                jokesAsync.add(future5.join());
                return jokesAsync;
        });
        jokes = combinedFuture.get();
        return jokes;

    }


}
