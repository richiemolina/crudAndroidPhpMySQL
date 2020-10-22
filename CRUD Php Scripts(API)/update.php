<?php

    $connection = mysqli_connect("localhost","id15172686_ricardoandroid","09#aR&CFW+vS&}F0","id15172686_crudandroid");
    
     $id = $_POST["id"];
     $nombre = $_POST["nombre"];
     $email = $_POST["email"];
     $contacto = $_POST["contacto"];
     
     $sql = "UPDATE datos SET  nombre = '$nombre', email = '$email', contacto = '$contacto' WHERE id = '$id' ";
     
     $result = mysqli_query($connection,$sql);
     
     if($result){
         echo "Data Updated";
        
     }
     else{
         echo "Failed";
     }
     mysqli_close($connection);
     
        
?>

