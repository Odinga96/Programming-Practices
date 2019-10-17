
import System.IO
import Control.Monad (replicateM)
import System.Random (randomRIO)

--
-- The width and the height of the canvas
--

width :: Int
width = 1024

height :: Int
height = 768

--
-- Max and minimum thicknesses of black lines.
--

maxThk :: Int
maxThk = 3

minThk :: Int
minThk = 1

--
-- Random Number Function
-- Generate and return a list of 20,000 random floating point numbers between 0 and 1
-- This function is used to add randomness to the output.
--
randomList :: IO [Float]
randomList = replicateM 200000 $ randomRIO (0.0, 1.0 :: Float)

--
-- Random Integer Function
-- Used to compute an integer from three inputs: an integer lower bound, and integer upper bound,
-- and a Float (between 0 and 1), returning some random value between the lower and the upper integers.
--
randomInt :: Int -> Int -> Float -> Int
randomInt lower upper p = round (fromIntegral(upper - lower) * p) + fromIntegral(lower)

--
-- Black Line Generator
-- Prints out the HTML tag to create a black line, given the following parameters: x coordinate, y coordinate,
-- width, and height.
--
genBlackLine :: Int -> Int -> Int -> Int -> String
genBlackLine x y w h =
   "<rect x=" ++ (show x) ++
   " y=" ++ (show y) ++
   " width=" ++ (show w) ++
   " height=" ++ (show h) ++
   " fill= \"black\"/> \n"

--
-- Randomly Coloured Rectangle Generator
-- Function, which accepts input of X-Coordinate, Y-Coordinate, Width, Height, and a list of floats
-- between 0 and 1, which represent (red, green, blue), and outputs an HTML tag string, outputting a square.
--
genColouredRect :: Int -> Int -> Int -> Int-> [Float] -> String
genColouredRect x y w h (r:g:b:rs) =
   "<rect x=" ++ (show x) ++
   " y=" ++ (show y) ++
   " width=" ++ (show w) ++
   " height=" ++ (show h) ++
   " style=\"fill:rgb(" ++ (show (round (r * 255))) ++ "," ++
                            (show (round (g * 255))) ++ "," ++
                            (show (round (b * 255))) ++ ")\" />\n"

genColouredRect x y w h _ =
   "<rect x=" ++ (show x) ++
   " y=" ++ (show y) ++
   " width=" ++ (show w) ++
   " height=" ++ (show h) ++
   " style=\"fill:rgb(" ++ (show (round (255))) ++ "," ++
                            (show (round (255))) ++ "," ++
                            (show (round (255))) ++ ")\" />\n"
--
--

genMondrian :: Int -> Int -> Int -> Int -> [Float] -> String
genMondrian x y w h [] = genColouredRect x y w h [0.0]
genMondrian x y w h (r:r2:rs)
-- 1) Split into 4 Large regions:
   | w > width `div` 2 && h > height `div` 2 =
      genMondrian x y (randomInt x w r) h (tail rs) ++
      genMondrian (x+(randomInt x w r)) y (w-(randomInt x w r)) h (tail rs) ++
      genBlackLine x y (randomInt minThk maxThk r) h
-- 2) Subdivide regions by height:
   | h > 500 =
     genMondrian x y w (randomInt y h r) (tail rs) ++
     genMondrian x (y+(randomInt y h r)) w (h-(randomInt y h r)) (tail rs) ++
     genBlackLine x y w (randomInt minThk maxThk r)
-- 3) Subdivide regions by width:
   | w > 500 =
     genMondrian x y (randomInt x w r) h (tail rs) ++
     genMondrian (x+randomInt x w r) y (w-(randomInt w x r)) h (tail rs) ++
     genBlackLine x y (randomInt minThk maxThk r) h
-- 4) Once subdivided, colour the remaining section.
   | otherwise = genColouredRect x y w h (tail rs) ++
     genBlackLine x y (randomInt minThk maxThk r) h ++ genBlackLine x y w (randomInt minThk maxThk r)


--
-- The main program, which generates and outputs a1.html
--
-- main :: IO ()
main = do
   randomValues <- randomList

   let heading = "<html><head></head><body>\n" ++
                "<svg width=\"" ++ (show width) ++
                "\" height=\"" ++ (show height) ++ "\">\n"

       rectangle = genMondrian 0 0 width height randomValues ++
               -- Create Frame for Canvas
               (genBlackLine 0 0 10 height) ++
               (genBlackLine 0 0 width 10) ++
               (genBlackLine (width-10) 0 10 height) ++
               (genBlackLine 0 (height-10) width 10)
       ending = "</svg>\n</html>"
   writeFile "mondrian.html" (heading ++ rectangle ++  ending)
