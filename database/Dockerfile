FROM mysql:8.0

# Initialize the database
COPY schema.sql /docker-entrypoint-initdb.d

ENV MYSQL_ROOT_PASSWORD=rootroot
