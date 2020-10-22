<?php

    $connection = mysqli_connect("localhost","id15172686_ricardoandroid","09#aR&CFW+vS&}F0","id15172686_crudandroid");
    
     $nombre = $_POST["nombre"];
     $email = $_POST["email"];
     $contacto = $_POST["contacto"];
     
     $sql = "INSERT INTO datos(nombre,email,contacto) VALUES ('$nombre','$email','$contacto')";
     
     $result = mysqli_query($connection,$sql);
     
     if($result){
         echo "Data Inserted";
        
     }
     else{
         echo "Failed";
     }
     mysqli_close($connection);
     
          
    
    


?>
