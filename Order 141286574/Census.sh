# !/ bin / sh
java Coordinator 12000 4 A B &
echo " Waiting ␣ for ␣ coordinator ␣ to ␣ start ... "
sleep 5
java Participant 12000 12346 5000 0 &
sleep 1
java Participant 12000 12347 5000 0 &
sleep 1
java Participant 12000 12348 5000 0 &
sleep 1
java Participant 12000 12349 5000 0 &