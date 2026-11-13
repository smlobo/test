<?php
echo nl2br("\nSample: garden_carnation\n");

try {
    $db = new PDO('mysql:host=localhost;dbname=mysql;charset=utf8mb4;012345678901234567890123456789012345678901234567890123456789', 'root', 'Oracle@123');
} catch (\PDOException $e) {
    echo nl2br("unexpected connection error: " . $e->getMessage());
    throw new \PDOException($e->getMessage(), (int)$e->getCode());
}

echo nl2br("expected: connected successfully with conn_string length more that 100 chars\n");

?>

