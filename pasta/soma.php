<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>soma</title>
</head>
<body>
    <form action="" method="get">
        <label for="inputa">A</label>
        <input type="number" id="inputA" name="A">

        <label for="inputa">B</label>
        <input type="number" id="inputB" name="b">

        <input type="submit" value="somar">
    </form>
    <?php if (isset($_GET['a'])) {?>
        <p>soma= <?php echo $_GET['a']+$_GET['b']; ?></p>
     <?php } ?>
     

    

    <?php
    echo $_GET['a'];
    echo $_GET['b'];
    echo $_GET['a']+$_GET['b']; 
     ?>
    
</body>
</html>