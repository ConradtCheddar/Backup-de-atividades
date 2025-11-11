<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <p>nome= <?php echo $_GET['nome']; ?></p>
    <p>email= <?php echo $_GET['email']; ?></p>
    <p>altura= <?php echo $_GET['altura']; ?></p>
    <p>peso= <?php echo $_GET['peso']; ?></p>

    <p>imc= <?php echo $_GET['peso']/($_GET['altura']*$_GET['altura']); ?></p>
    
</body>
</html>