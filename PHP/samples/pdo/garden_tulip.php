<?php
echo nl2br("\nSample: garden_tulip\n");

try {
    $db = new PDO('mysql:host=localhost;dbname=mysql;charset=utf8mb4', 'root', 'Oracle@123');
    $db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $db->setAttribute(PDO::ATTR_EMULATE_PREPARES, false);
} catch (\PDOException $e) {
    echo nl2br("unexpected: connection error: " . $e->getMessage(). "\n");
    throw new \PDOException($e->getMessage(), (int)$e->getCode());
}
echo nl2br("expected: connected successfully\n");

$sql = "select a.table_name, a.table_comment from information_schema.tables_wrong a where a.table_comment <> '' limit 3;";
try {
    $i = $db->exec($sql);
    echo nl2br("unexpected: db->exec returns " . $i);
} catch (\PDOException $e) {
    echo nl2br("expected: query error: " . $e->getMessage(). "\n");
    throw new \PDOException($e->getMessage(), (int)$e->getCode());
}

echo nl2br("unexpected: pdo::exec succeeded");

?>

