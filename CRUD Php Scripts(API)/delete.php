<?php

$connection = mysqli_connect("localhost","id15172686_ricardoandroid","09#aR&CFW+vS&}F0","id15172686_crudandroid");
    
     $id = $_POST["id"];
     
     $sql = "DELETE FROM datos WHERE id='$id'";
     
     $result = mysqli_query($connection,$sql);
     
     if($result){
         echo "Data Deleted";
        
     }
     else{
         echo "Failed";
     }
     mysqli_close($connection);
     


?>
