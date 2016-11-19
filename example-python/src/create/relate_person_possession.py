'''
Created on 2016年5月13日

@author: keshengkai
'''

import mysql.connector;
import random;

conn = mysql.connector.Connect(host='localhost', user='root', password='abc123',database='test')

c = conn.cursor()

i = 0
count = 0;
originSql = """insert into person_possession(person_id, possession_id) values """
sql = originSql;

c.execute("select count(*) from person_possession");

recordSize = 0;
for row in c:
    recordSize = row[0]

if(recordSize < 20000000):
    size = 10000000 - recordSize;
random.seed(12);
while i < size:  #approximately 16 million
    sql += "(" + str(random.randint(1, 20000000)) + "," + str(random.randint(1, 20000000)) + "),"
    count += 1
    if(count == 1000):
        c.execute(sql[0 : len(sql) - 1]);
#         print(sql[0 : len(sql) - 1]);
        sql = originSql
        count = 0
        print(i);
    i += 1
    
conn.commit()
c.close()