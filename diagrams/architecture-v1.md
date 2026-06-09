## High Level Architecture

```text
+-------------+
|    User     |
+------+------+
       |
       v
+------------------+
|  Alert Service   |
+--------+---------+
         |
         | Publish Event
         v
+------------------+
|      Kafka       |
+--------+---------+
         |
         | Consume Event
         v
+------------------+
| Incident Service |
+--------+---------+
         |
         v
+------------------+
|   PostgreSQL     |
+------------------+
```