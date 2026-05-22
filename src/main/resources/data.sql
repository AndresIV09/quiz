-- =============================================
-- DATA.SQL - Datos iniciales para la BD
-- Proyecto: Quiz (Spring Boot + PostgreSQL/Neon)
-- =============================================

-- ASOCIACIONES
INSERT INTO asociaciones (nombre, pais, presidente) VALUES
  ('Federación Colombiana de Fútbol', 'Colombia', 'Ramón Jesurún'),
  ('Real Federación Española de Fútbol', 'España', 'Rafael Louzan'),
  ('Federación Brasileña de Fútbol', 'Brasil', 'Ednaldo Rodrigues'),
  ('Federación Argentina de Fútbol', 'Argentina', 'Claudio Tapia'),
  ('Federación Mexicana de Fútbol', 'México', 'Ivar Sisniega'),
  ('Federación Inglesa de Fútbol', 'Inglaterra', 'Mark Bullingham'),
  ('Federación Alemana de Fútbol', 'Alemania', 'Bernd Neuendorf'),
  ('Federación Francesa de Fútbol', 'Francia', 'Philippe Diallo')
ON CONFLICT DO NOTHING;

-- ENTRENADORES
INSERT INTO entrenadores (nombre, apellido, edad, nacionalidad) VALUES
  ('Néstor',    'Lorenzo',     55, 'Argentina'),
  ('Carlo',     'Ancelotti',   65, 'Italia'),
  ('Pep',       'Guardiola',   53, 'España'),
  ('Jürgen',    'Klopp',       57, 'Alemania'),
  ('Diego',     'Simeone',     54, 'Argentina'),
  ('Xavi',      'Hernández',   45, 'España'),
  ('Zinedine',  'Zidane',      52, 'Francia'),
  ('Mauricio',  'Pochettino',  52, 'Argentina'),
  ('Jorge',     'Luis Pinto',  68, 'Colombia'),
  ('Hernán',    'Darío Gómez', 71, 'Colombia'),
  ('Luis',      'Enrique',     54, 'España'),
  ('Antonio',   'Conte',       55, 'Italia')
ON CONFLICT DO NOTHING;

-- COMPETICIONES
INSERT INTO competiciones (nombre, monto_premio, fecha_inicio, fecha_fin) VALUES
  ('Liga BetPlay Dimayor',      2000000,  '2025-01-25', '2025-12-15'),
  ('Copa Colombia',              500000,  '2025-02-10', '2025-11-30'),
  ('Liga Española LaLiga',     15000000,  '2025-08-15', '2026-05-25'),
  ('Copa del Rey',              2000000,  '2025-10-01', '2026-05-30'),
  ('Premier League',           25000000,  '2025-08-10', '2026-05-24'),
  ('Champions League',         80000000,  '2025-09-17', '2026-05-31'),
  ('Copa Libertadores',         7000000,  '2025-02-04', '2025-11-29'),
  ('Copa Sudamericana',         4000000,  '2025-03-05', '2025-11-22'),
  ('Bundesliga',               10000000,  '2025-08-22', '2026-05-16'),
  ('Serie A',                  12000000,  '2025-08-18', '2026-05-18')
ON CONFLICT DO NOTHING;

-- CLUBES
INSERT INTO clubes (nombre, ciudad, ano_fundacion, entrenador_id, asociacion_id) VALUES
  ('Atlético Nacional',     'Medellín',    1947,  9, 1),
  ('Millonarios FC',        'Bogotá',      1946, 10, 1),
  ('América de Cali',       'Cali',        1927,  9, 1),
  ('Deportivo Cali',        'Cali',        1912, 10, 1),
  ('Junior de Barranquilla','Barranquilla', 1924,  9, 1),
  ('Real Madrid CF',        'Madrid',      1902,  2, 2),
  ('FC Barcelona',          'Barcelona',   1899,  6, 2),
  ('Atlético de Madrid',    'Madrid',      1903,  5, 2),
  ('Manchester City',       'Manchester',  1880,  3, 6),
  ('Liverpool FC',          'Liverpool',   1892,  4, 6),
  ('Bayern München',        'Múnich',      1900, 11, 7),
  ('Paris Saint-Germain',   'París',       1970,  8, 8)
ON CONFLICT DO NOTHING;

-- JUGADORES
INSERT INTO jugadores (nombre, apellido, numero, posicion) VALUES
  ('Jefferson',  'Duque',        1,  'Portero'),
  ('Andrés',     'Mosquera',     4,  'Defensa'),
  ('Baldomero',  'Perlaza',      8,  'Mediocampista'),
  ('Dayro',      'Moreno',       9,  'Delantero'),
  ('Sebastián',  'Gómez',       10,  'Mediocampista'),
  ('David',      'Macalister',   7,  'Delantero'),
  ('Fernando',   'Uribe',        9,  'Delantero'),
  ('Albert',     'Campaz',      11,  'Delantero'),
  ('Falcao',     'García',       9,  'Delantero'),
  ('Luis',       'Díaz',        23,  'Delantero'),
  ('Vinicius',   'Junior',       7,  'Delantero'),
  ('Kylian',     'Mbappé',       9,  'Delantero'),
  ('Erling',     'Haaland',      9,  'Delantero'),
  ('Pedri',      'González',     8,  'Mediocampista'),
  ('Thibaut',    'Courtois',     1,  'Portero'),
  ('Lamine',     'Yamal',       19,  'Delantero'),
  ('Toni',       'Kroos',        8,  'Mediocampista'),
  ('Bukayo',     'Saka',         7,  'Delantero'),
  ('Jamal',      'Musiala',     42,  'Mediocampista'),
  ('Antoine',    'Griezmann',    7,  'Delantero'),
  ('Kevin',      'De Bruyne',   17,  'Mediocampista'),
  ('Mohamed',    'Salah',       11,  'Delantero'),
  ('Harry',      'Kane',         9,  'Delantero'),
  ('Rodri',      'Hernández',   16,  'Mediocampista'),
  ('Gavi',       'Páez',         6,  'Mediocampista'),
  ('Robert',     'Lewandowski',  9,  'Delantero'),
  ('Manuel',     'Neuer',        1,  'Portero'),
  ('Virgil',     'Van Dijk',     4,  'Defensa'),
  ('Carlos',     'Cuesta',       5,  'Defensa'),
  ('Miguel',     'Borja',        9,  'Delantero')
ON CONFLICT DO NOTHING;

-- RELACIÓN CLUB-COMPETICIÓN
-- Tabla: clubes_competiciones (nombre generado por Hibernate)
-- clubes: Nacional=1, Millonarios=2, América=3, Cali=4, Junior=5,
--         Real Madrid=6, Barcelona=7, Atlético Madrid=8,
--         Man City=9, Liverpool=10, Bayern=11, PSG=12
-- competiciones: BetPlay=1, Copa Col=2, LaLiga=3, Copa Rey=4,
--                Premier=5, Champions=6, Libertadores=7, Sudamericana=8,
--                Bundesliga=9, Serie A=10
INSERT INTO clubes_competiciones (club_id, competiciones_id) VALUES
  (1, 1), (1, 2), (1, 7),
  (2, 1), (2, 2), (2, 7),
  (3, 1), (3, 2), (3, 8),
  (4, 1), (4, 2),
  (5, 1), (5, 2), (5, 8),
  (6, 3), (6, 4), (6, 6),
  (7, 3), (7, 4), (7, 6),
  (8, 3), (8, 4), (8, 6),
  (9, 5), (9, 6),
  (10, 5), (10, 6),
  (11, 9), (11, 6),
  (12, 6)
ON CONFLICT DO NOTHING;