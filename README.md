# API Gateway - Punto de Entrada Único

Spring Cloud Gateway 4.0.x que enruta todas las peticiones del frontend a los microservicios correspondientes del sistema de emergencias Valle del Sol.

## Enrutamiento

| Ruta | Destino | Stripeo de prefijo |
|---|---|---|
| `/api/usuario/**` | `lb://user-service` | Sí |
| `/api/reportes/**` | `lb://report-service` | Sí |
| `/api/alertas/**` | `lb://alert-service` | Sí |
| `/api/bff/**` | `lb://bff-service` | Sí |

El gateway además:
- Usa **discovery locator** para enrutamiento dinámico
- Configura **CORS** para el frontend
- Balancea carga con **LoadBalancer** de Spring Cloud

## Tecnologías

- Spring Cloud Gateway 4.0.x / Java 21
- Eureka Client
- Spring Cloud LoadBalancer
- JaCoCo (cobertura ≥ 60%)

## Tests

```bash
mvnw test
```
