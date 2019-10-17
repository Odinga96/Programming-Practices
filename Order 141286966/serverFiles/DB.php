<?php

/*
    The database class used to handle all interactions to the database
*/
    class DBconnect{
    	private $servername;
    	private $user_Name;
    	private $password;
    	private $database_Name;
    	private $charset;

      protected function connect()  {
      	$this->servername="127.0.0.1";
      	$this->user_Name="root";
      	$this->password="";
      	$this->database_Name="Cofee_Buz";
      	$this->charset="utf8mb4";


      	   try {
      	   	$link="mysql:host=".$this->servername.";
                        dbname=".$this->database_Name.";
                        charset=".$this->charset;

      	   	$pdo=new PDO($link,$this->user_Name,$this->password);
      	   	$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
      	   	return $pdo;

      	   } catch (Exception $e) {
      	   	 echo "Could not connect ".$e->getMessage();
      	   }
      }

    //This function is used to check if an item is already in the database
    //If the item is available returns true
    //This method is also used for login  since it returns true or false
  protected function itemExists($Query, $Primary_Key){
        $run_query=$this->connect()->prepare($Query);
        $run_query->execute([$Primary_Key]);
        $row=$run_query->rowCount();
           if ($row>0)
            return true;
            else
              return false;
      }


//Adds a new item to the database. Irrigardless of the  of the table it takes a query and adds the item directly
  protected function register_item($Query, $Value,$Redirectory,$redirect){
                    $run_query=$this->connect()->prepare($Query);

                    if($run_query->execute()){
                        echo "<script>alert('You sucessfylly added  ".$Value."')</script>";

                        //redirect should take a boolean value . If true we redirect to a new page
                        if($redirect)
                        echo "<script>window.open('".$Redirectory."','_self')</script>";
                      }else
                      echo "<script>alert('Connection problem')</script>";

          }

//Delete and item from a table
          protected function removeItem($Query,$check_query,$ID,$item_to_remove){
            if ($this->itemExists($check_query,$ID)) {
                  $run_query=$this->connect()->prepare($Query);
                  $run_query->execute([$ID]);
            }else{

              echo "<script>alert('".$item_to_remove." with ".$ID." does not exist')</script>";
            }
          }


          protected function updateItem($Query,$check_query,$ID,$item_to_remove){
            if ($this->itemExists($check_query,$ID)) {
                  $run_query=$this->connect()->prepare($Query);
                  $run_query->execute([$ID]);
            }else{

              echo "<script>alert('".$item_to_remove." with ".$ID." does not exist')</script>";
            }
          }
    }

?>
