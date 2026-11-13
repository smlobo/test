<?php
echo nl2br("\nSample: garden_lily\n");

try {
    $db = new PDO('mysql:host=localhost;dbname=mysql;charset=utf8mb4', 'root', 'Oracle@123_wrong');
} catch (\PDOException $e) {
    echo nl2br("expected connection error: " . $e->getMessage());
    throw new \PDOException($e->getMessage(), (int)$e->getCode());
}

echo nl2br("unexpected: connected successfully\n");

?>

