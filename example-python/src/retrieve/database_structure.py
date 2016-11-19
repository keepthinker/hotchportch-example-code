import mysql.connector;

conn = mysql.connector.Connect(host='192.168.2.240', port=3337,  user='testromsp', password='testromsp', database='test')

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
