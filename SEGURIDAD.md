# 🔐 Guía de Seguridad — Curriculum Web

## Variables de entorno en Render (OBLIGATORIO cambiar)

En el dashboard de Render → tu servicio → **Environment**, configura:

| Variable         | Descripción                          | Ejemplo seguro         |
|------------------|--------------------------------------|------------------------|
| `ADMIN_USERNAME` | Usuario del panel admin              | `miusuario`            |
| `ADMIN_PASSWORD` | Contraseña del panel admin           | `M1C0ntr@s3ña!2024`    |
| `DB_USERNAME`    | Usuario de la BD (auto con Render DB)| (provisto por Render)  |
| `DB_PASSWORD`    | Contraseña BD (auto con Render DB)   | (provisto por Render)  |
| `SESSION_SECURE` | `true` en producción (HTTPS)         | `true`                 |

## Características de seguridad implementadas

### Autenticación
- Login con usuario/contraseña protegido con BCrypt (factor 12)
- Sesión expira en 30 minutos de inactividad
- Cookie de sesión: HttpOnly + SameSite=Strict + Secure en producción

### Protección de rutas
- `/` — público (el CV)
- `/login` — público
- `/editar` y `/guardar` — solo rol ADMIN (requiere login)

### Cabeceras HTTP
- `X-Frame-Options: DENY` — previene clickjacking
- `X-XSS-Protection: 1; mode=block`
- `Content-Security-Policy` — restringe scripts/estilos a CDNs autorizados
- `Referrer-Policy: strict-origin-when-cross-origin`

### CSRF
- Token CSRF en todos los formularios POST
- Los errores de servidor nunca exponen stack traces

## Cómo acceder al panel admin
1. Abre tu CV → botón **Admin** (esquina inferior izquierda)
2. Ingresa usuario y contraseña
3. Edita tu CV y guarda
4. Usa "Cerrar sesión" cuando termines

## Recomendaciones adicionales
- Usa una contraseña de mínimo 12 caracteres con mayúsculas, números y símbolos
- Nunca compartas las variables de entorno
- Activa los alertas de Render para monitorear el servicio
