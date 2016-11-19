import mysql.connector;
import time;
conn =  mysql.connector.Connect(host = "192.168.2.97", port = "3302", user = "testcomm", password = "testcomm", database = "comm");

c = conn.cursor()
s = int(round(time.time() * 1000));
c.execute("show tables from comm");
e = int(round(time.time() * 1000));
print(e - s)

for row in c:
    print(row)