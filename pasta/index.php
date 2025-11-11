<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    minha pagina html
    <?php
    echo "ola<BR>" 
    $nome="Conradt<BR>" 
    echo $nome."<BR>"
    ?>
    <h2> exemplo de tipos de dados</h2>
    <?php
    $a=5;
    $b=7;
    echo "<p>".$a+$b."<p>";
    ?>
    <p> <?php echo .$a*$b.?> </p>
    <h2>operador ternario</h2>
    <?php
    $nota=9.9;
    $situacao = $nota >= 6 ? 'aprovado' : 'reprovado';
    echo $situacao;
    ?>
    <h2>while</h2>
    <?php
    $i = 0;
    while ($i<10){
        $i++;
        echo "i=".$i." - ";
    }
    ?>
    <h2>array em php</h2>
    <?php
    $vetor = array(20,19,22,19,23);
    for ($posicao=0; $posicao < 5; $posicao++) { 
        echo $vetor[$posicao] . '<br />'
    }
    foreach($vetor as $elemento){
        echo "elemento :".$elemento;
    }
    ?>
    <?php
    $vetor = array(
        'nome' => 'guilherme',
        'sobrenome' => 'da silva',
        'cpf' => '123.456.789.09'
    );
    echo 'nome: '.$vetor['nome'].'<br />';
    echo 'sobrenome: '.$vetor['sobrenome'].'<br />';
    echo 'cpf: '.$vetor['cpf'].'<br />';
    ?>
    


    
</body>
</html>