import mysql.connector;

conn = mysql.connector.Connect(host='192.168.0.112', port=3306,  user='test', password='test', database='test')

c = conn.cursor()

c.execute("select * From information_schema.TABLES");

count = 0;
for row in c:
    print(row)
    count += 1
    print(count);
    
    
c.execute("show tables from information_schema");

count = 0;
for row in c:
    print(row)
    count += 1
    print(count);
    
c.close()
