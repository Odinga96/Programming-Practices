package com.company;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MazeTest {

    private String queueFail = "queue test fail.";
    private String stackFail = "stack test fail.";

    @Test(timeout = 1000)
    public void test1() {
        char[][] map = toMap("$#####\n" +
                ".....#\n" +
                "####%#\n" +
                "######");
        String result = "(0,0)(1,0)(1,1)(1,2)(1,3)(1,4)(2,4) 6";
        String result1 = "(0,0)(1,0)(1,1)(1,2)(1,3)(1,4)(2,4) 6";
        Maze maze = new Maze();

        System.out.println("1111111111111:: " + maze.solveWithQueue(map));
        System.out.println("11111111111111:: " + maze.solveWithStack(map));


        assertEquals(queueFail, maze.solveWithQueue(map) ,result);
        assertEquals(stackFail, maze.solveWithStack(map) ,result1);
    }

    @Test(timeout = 1000)
    public void test2() {
        char[][] map = toMap(
                "$#########\n" +
                    "..#.....##\n" +
                    "#.######.#\n" +
                    "#.......##\n" +
                    "##.#.#%###\n" +
                    "##########");
        String result = "(0,0)(1,0)(1,1)(2,1)(3,1)(3,2)(3,3)(3,4)(3,5)(3,6)(4,6) 12";
        String result1 = "(0,0)(1,0)(1,1)(2,1)(3,1)(3,2)(3,3)(3,4)(3,5)(3,6)(4,6) 10";

        Maze maze = new Maze();

        System.out.println("~~~~~~~~~~~:: " + maze.solveWithQueue(map));
        System.out.println("~~~~~~~~~~~:: " + maze.solveWithStack(map));


        //System.out.println("RESULT !!!!:: " + toMap());

        assertEquals(queueFail, maze.solveWithQueue(map) ,result);
        assertEquals(stackFail, maze.solveWithStack(map) ,result1);

    }

    @Test(timeout = 1000)
    public void test3() {
        char[][] map = toMap(
                "$#########\n" +
                    "........##\n" +
                    "#.######.#\n" +
                    "#......%##\n" +
                    "##.#.#####\n" +
                    "##########");
        String result = "(0,0)(1,0)(1,1)(2,1)(3,1)(3,2)(3,3)(3,4)(3,5)(3,6)(3,7) 18";
        String result1 = "(0,0)(1,0)(1,1)(2,1)(3,1)(3,2)(3,3)(3,4)(3,5)(3,6)(3,7) 16";
        Maze maze = new Maze();


        System.out.println("222222222:: " + maze.solveWithQueue(map));
        System.out.println("222222222:: " + maze.solveWithStack(map));


        assertEquals(queueFail, maze.solveWithQueue(map) ,result);
        assertEquals(stackFail, maze.solveWithStack(map) ,result1);

    }

    private char[][] toMap(String str){
        String[] lines = str.split("\n");
        char[][] map = new char[lines.length][];
        for(int i = 0; i < lines.length; i++){
            map[i] = lines[i].toCharArray();
        }
        return map;
    }
}

