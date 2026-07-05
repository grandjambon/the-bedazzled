# The BeDazzled

Statistics site for BeDazzled — an amateur 5/6-a-side football team. Tracks seasons, matches, player appearances, goals, opponents, records, streaks, and accounts across 42 seasons of match reports.

## How it works

The site is generated from match report text files in `src/main/resources/seasons/`. A Spring Boot app parses these files and renders HTML pages via Freemarker templates. A static export step crawls the running app with `wget --mirror` to produce a fully static site, which is then deployed to GitHub Pages.

## Development

**Prerequisites:** Java 21, Maven 3.8+

```bash
# Run locally
mvn spring-boot:run
# Open http://localhost:8080

# Run tests
mvn test

# Export static site
./export-static-site.sh
# Serve locally: cd target/static-site && python3 -m http.server 9090
```

## Deployment

Deployment is automatic via GitHub Actions. Pushing to `master` triggers the workflow which:

1. Builds the project with Maven
2. Starts the app and crawls all pages with `wget --mirror`
3. Deploys the static HTML to GitHub Pages

To configure: go to repo **Settings → Pages** and set source to "GitHub Actions".

## Adding a new season

1. Create a new directory under `src/main/resources/seasons/` (e.g. `season43/`)
2. Add match report text files in the `reports/` subdirectory
3. Commit and push to `master` — the site will rebuild and deploy automatically
