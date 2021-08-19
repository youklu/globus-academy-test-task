CREATE TABLE IF NOT EXISTS public.multiple_container_metrics
(
    id SERIAL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.container (
    id SERIAL,
    container_id INTEGER,
    multiple_container_metrics_id integer,
    PRIMARY KEY (id),
    FOREIGN KEY (multiple_container_metrics_id) REFERENCES multiple_container_metrics(ID) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS public.container_metrics (
    id SERIAL,
  	"timestamp" TIMESTAMP NOT NULL,
  	container_id INTEGER,
    PRIMARY KEY (id),
    FOREIGN KEY (container_id) REFERENCES container(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS public.metric (
    id SERIAL,
  	name VARCHAR(255) NOT NULL,
  	value INTEGER NOT NULL,
  	container_metrics_id INTEGER,
    PRIMARY KEY (id),
    FOREIGN KEY (container_metrics_id) REFERENCES container_metrics(id) ON DELETE CASCADE
);
