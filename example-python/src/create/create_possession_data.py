import mysql.connector;
import random;

conn = mysql.connector.Connect(host='localhost', user='root', password='abc123',database='test')

c = conn.cursor()

i = 0
count = 0;
originSql = """insert into possession(name) values """
sql = originSql;

c.execute("select count(*) from possession");

recordSize = 0;
for row in c:
    recordSize = row[0]

if(recordSize < 10000000):
    size = 10000000 - recordSize;
    
while i < size:  #approximately 16 million
    sql += "('" + str(random.randint(1, 10000000)) + ":" + str(i) + "'),"
    count += 1
    if(count == 1000):
        c.execute(sql[0 : len(sql) - 1]);
        sql = originSql
        count = 0
        print(i);
    i += 1
    
conn.commit()
c.close()