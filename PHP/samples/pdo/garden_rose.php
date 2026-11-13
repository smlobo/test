<?php
echo nl2br("\nSample: garden_rose\n");

try {
    $db = new PDO('mysql:host=localhost;dbname=mysql;charset=utf8mb4', 'root', 'Oracle@123');
    $db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $db->setAttribute(PDO::ATTR_EMULATE_PREPARES, false);
} catch (\PDOException $e) {
    echo nl2br("unexpected connection error: " . $e->getMessage(). "\n");
    throw new \PDOException($e->getMessage(), (int)$e->getCode());
}
echo nl2br("connected successfully\n");

?>


