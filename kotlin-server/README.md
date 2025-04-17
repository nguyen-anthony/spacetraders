# SpaceTraders Kotlin Server

A Kotlin-based server for interacting with the SpaceTraders API, built with Micronaut.

## Prerequisites

- JDK 17 or higher
- Gradle 8.0 or higher
- A SpaceTraders API token (get one from [SpaceTraders](https://spacetraders.io/))

## Setup

1. Clone the repository:
```bash
git clone <repository-url>
cd spacetraders/kotlin-server
```

2. Create a `.env` file in the project root with your SpaceTraders API token:
```bash
echo "SPACETRADERS_TOKEN=your_token_here" > .env
```

3. Build the project:
```bash
./gradlew build
```

## Running the Server

To start the server in development mode:
```bash
./gradlew run
```

The server will start on port 8080 by default. You can change this by setting the `PORT` environment variable.

## API Endpoints

### GET /api/waypoints/headquarters
Returns information about your agent's headquarters waypoint.

Example response:
```json
{
  "data": {
    "symbol": "X1-DF55-20250Z",
    "type": "PLANET",
    "systemSymbol": "X1-DF55",
    "x": 0,
    "y": 0,
    "orbitals": [...],
    "traits": [...],
    "chart": {...},
    "faction": {...}
  }
}
```

## Development

### Project Structure
- `src/main/kotlin/com/spacetraders/`
  - `client/` - API client interfaces
  - `config/` - Configuration classes
  - `models/` - Data models
  - `services/` - Business logic
  - `controllers/` - HTTP endpoints

### Adding New Endpoints
1. Create a new controller class in `controllers/`
2. Define your endpoint methods with appropriate annotations
3. Create any necessary models in `models/`
4. Add service logic in `services/`

### Testing
Run the test suite:
```bash
./gradlew test
```

## Environment Variables

- `SPACETRADERS_TOKEN` - Your SpaceTraders API token (required)
- `PORT` - Port to run the server on (default: 8080)

## Troubleshooting

### Common Issues

1. **API Token Not Found**
   - Ensure your `.env` file exists and contains the `SPACETRADERS_TOKEN` variable
   - Verify the token is valid by checking the SpaceTraders website

2. **Build Failures**
   - Make sure you have JDK 17 installed
   - Try cleaning the project: `./gradlew clean build`

3. **Server Not Starting**
   - Check if port 8080 is already in use
   - Verify all environment variables are set correctly

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

[Add your license information here] 