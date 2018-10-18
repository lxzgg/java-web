package com.web.utils;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

class AsyncTest {

    @Test
    void thenApply_join_get() {
        var supplyAsync1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("task1 doing...");
            try {
                Thread.sleep(1111);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "result1";
        });
        // thenApply等待结果,处理结果
        var result = supplyAsync1.thenApply(s -> s + " new");
        System.out.println(result.join());
        try {
            System.out.println(result.get());
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("get()方法可以处理异常");
            System.out.println("join()方法不能处理异常");
        }
    }

    @Test
    void thenAccept() {
        // thenAccept等待结果,进行消耗
        var supplyAsync1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("task1 doing...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "result1";
        });

        var supplyAsync2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("task2 doing...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "result2";
        });

        supplyAsync1.thenAccept(s -> System.out.println("第一个完成后,在这里执行操作," + s));
        supplyAsync2.thenAccept(s -> System.out.println("第二个完成后,在这里执行操作," + s));

        var supplyAsync3 = supplyAsync1.thenCombine(supplyAsync2, (result1, result2) -> {
            System.out.println("task3 doing...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return result1 + " " + result2;
        });
        // supplyAsync2会和supplyAsync3同时执行结果
        System.out.println(supplyAsync3.join());
    }

    @Test
    void thenCompose() {
        var supplyAsync1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("task1 doing...");
            try {
                Thread.sleep(1001);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "result1";
        });
        // 等supplyAsync1完成后,结果传给参数result,开始第二个异步supplyAsync2
        var anyResult2 = supplyAsync1.thenCompose(result -> CompletableFuture.supplyAsync(() -> {
            System.out.println("task2 doing...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "result2";
        }));
        System.out.println(anyResult2.join());
    }

    @Test
    void thenCombine() {
        var supplyAsync1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("task1 doing...");
            try {
                Thread.sleep(1003);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "result1";
        });
        //将第一个任务与第二个任务组合一起执行，都执行完成后，将两个任务的结果合并
        var anyResult2 = supplyAsync1.thenCombine(
                CompletableFuture.supplyAsync(() -> {
                    System.out.println("task2 doing...");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "result2";
                }),
                (s, s2) -> s + " " + s2
        );
        System.out.println(anyResult2.join());
    }

    @Test
    void thenAcceptBoth() {
        // 并行,利用这两个返回值，进行消耗
        CompletableFuture.supplyAsync(() -> {
            System.out.println("task1 doing...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }).thenAcceptBoth(CompletableFuture.supplyAsync(() -> {
            System.out.println("task2 doing...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "world";
        }), (s1, s2) -> System.out.println(s1 + " " + s2)).join();
    }

    @Test
    void applyToEither() {
        // 并行,找一个最快的结果进行处理
        String result = CompletableFuture.supplyAsync(() -> {
            System.out.println("task1 doing...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "s1";
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            System.out.println("task2 doing...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello world";
        }), s -> s).join();
        System.out.println(result);
    }

    @Test
    void acceptEither() {
        // 并行,有返回值,找一个最快的结果进行消耗
        CompletableFuture.supplyAsync(() -> {
            System.out.println("task1 doing...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "s1";
        }).acceptEither(CompletableFuture.supplyAsync(() -> {
            System.out.println("task2 doing...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello world";
        }), System.out::println).join();
    }

    @Test
    void thenRun() {
        var supplyAsync1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("task1 doing...");
            try {
                Thread.sleep(1112);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "result1";
        });
        supplyAsync1.thenRun(() -> System.out.println("thenRun上一步的计算结果不关心，执行下一个操作")).join();
    }

    @Test
    void runAfterBoth() {
        // 并行,不关心结果,这两个执行完毕,进行消耗
        CompletableFuture.supplyAsync(() -> {
            System.out.println("task1 doing...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "s1";
        }).runAfterBothAsync(CompletableFuture.supplyAsync(() -> {
            System.out.println("task2 doing...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            return "s2";
        }), () -> System.out.println("hello world")).join();
    }

    @Test
    void runAfterEither() {
        // 并行,无返回值,找一个最快的结果进行消耗
        CompletableFuture.supplyAsync(() -> {
            System.out.println("task1 doing...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "s1";
        }).runAfterEither(CompletableFuture.supplyAsync(() -> {
            System.out.println("task2 doing...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "s2";
        }), () -> System.out.println("hello world")).join();
    }

    @Test
    void whenComplete() {
        // 只能在whenComplete处理结果,result只能拿到异常
        String result = CompletableFuture.supplyAsync(() -> {
            if (Math.random() > 0.5) {
                throw new RuntimeException("测试一下异常情况");
            }
            return "s1";
        }).whenComplete((s, t) -> {
            System.out.println(s);
            System.out.println(t.getMessage());
        }).exceptionally(e -> {
            System.out.println(e.getMessage());
            return "hello world";
        }).join();
        System.out.println(result);
    }

    @Test
    void handle() {
        // 可以选择性返回异常,或结果
        String result = CompletableFuture.supplyAsync(() -> {
            if (Math.random() > 0.5) {
                throw new RuntimeException("测试一下异常情况");
            }
            return "s1";
        }).handle((s, t) -> {
            if (t != null) {
                return "hello world";
            }
            return s;
        }).join();
        System.out.println(result);
    }

    @Test
    void anyOf_allOf() throws ExecutionException, InterruptedException {
        var supplyAsync1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("task1 doing...");
            try {
                Thread.sleep(1002);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "result1";
        });
        CompletableFuture<String> supplyAsync2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("task2 doing...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "result2";
        });
        // 后面的先执行
        var anyResult = CompletableFuture.anyOf(supplyAsync1, supplyAsync2);
        // anyOf只要有完成的就返回
        System.out.println("第一个完成的任务结果:" + anyResult.get());

        var allResult = CompletableFuture.allOf(supplyAsync1, supplyAsync2);
        allResult.join();
        // allOf阻塞等待所有任务执行完成
        System.out.println("所有任务执行完成");
    }

}