DROP TABLE IF EXISTS TODOList;
DROP TYPE  IF EXISTS taskStatus;

CREATE TYPE taskStatus as ENUM('In_progress','Pending','Finished');

CREATE TABLE TODOList (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  status taskStatus NOT NULL,
  content VARCHAR(256) NOT NULL
);

INSERT INTO TODOList (id, status, content) VALUES
  (1,'In_progress', 'Backend , microservicios con Spring boot'),
  (2,'Pending', 'Repositorio proyecto'),
  (3,'Finished', 'Devops, desplegar imagen de la aplicacion desde dockerhub en AKS');