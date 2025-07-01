# Home Assistant Add-on: Kiwix

_Offline content reader for Wikipedia and other content via ZIM files._

![Supports aarch64 Architecture][aarch64-shield]
![Supports amd64 Architecture][amd64-shield]
![Supports armhf Architecture][armhf-shield]
![Supports armv7 Architecture][armv7-shield]
![Supports i386 Architecture][i386-shield]

## About

Kiwix is a free and open-source offline content reader that allows you to access educational content like Wikipedia, Wiktionary, TED talks, and more without an internet connection. This Home Assistant add-on packages Kiwix into an easy-to-use format that integrates seamlessly with your Home Assistant installation.

## Features

- 🌐 Offline access to Wikipedia and other educational content
- 📚 Support for ZIM files (compressed content format)
- 🏠 **Integrated with Home Assistant sidebar** for easy access
- 🔧 Easy configuration through Home Assistant UI
- 📁 Flexible library path configuration
- 🌍 Multi-language content support
- 🚀 Fast content serving with built-in HTTP server
- ⚡ Multi-threaded performance optimization
- 🔍 Configurable full-text search across multiple ZIM files
- 🔒 Security options to block external links
- 🎨 Customizable interface (hide/show search bar and library button)
- 📂 Automatic detection and serving of entire ZIM directories

## Installation

1. Navigate in your Home Assistant frontend to **Settings** -> **Add-ons** -> **Add-on Store**.
2. Add this repository URL to your Home Assistant supervisor.
3. Find the "Kiwix" add-on and click it.
4. Click on the "INSTALL" button.

## How to use

1. **Install the add-on** (see above)
2. **Download ZIM files** from [library.kiwix.org](https://library.kiwix.org/)
3. **Copy ZIM files** to your configured library path (default: `/share/kiwix/`)
4. **Start the add-on**
5. **Access content**:
   - **Via Home Assistant sidebar**: Click "Kiwix" in the sidebar (recommended and secure)
   - The add-on integrates seamlessly into Home Assistant's interface

### Popular ZIM files to try:
- **Wikipedia** (available in many languages)
- **Wiktionary** (multilingual dictionary)
- **TED Talks** (educational videos and transcripts)
- **Project Gutenberg** (free ebooks)
- **Stack Overflow** (programming Q&A)

## Configuration

Add the following configuration to your `config.yaml`:

```yaml
log_level: info
library_path: /share/kiwix
threads: 4
search_limit: 0
block_external_links: false
no_search_bar: false
no_library_button: false
```

### Option: `log_level` (optional)
The log level for Kiwix server output.
Valid options: `trace`, `debug`, `info`, `notice`, `warning`, `error`, `fatal`
Default: `info`

### Option: `library_path` (optional)
Path where ZIM files are stored. This path should be accessible from within the container.
Default: `/share/kiwix`

### Option: `threads` (optional)
Number of server threads for handling requests. Higher values may improve performance with concurrent users.
Range: 1-16
Default: `4`

### Option: `search_limit` (optional)
Maximum number of search results to return (0 = unlimited).
Range: 0-100
Default: `0`

### Option: `block_external_links` (optional)
Block access to external links when browsing content for security.
Default: `false`

### Option: `no_search_bar` (optional)
Hide the search bar in the Kiwix interface.
Default: `false`

### Option: `no_library_button` (optional)
Hide the library button in the Kiwix interface.
Default: `false`

## Support

In case you've found a bug, please [open an issue on our GitHub][issue].

[aarch64-shield]: https://img.shields.io/badge/aarch64-yes-green.svg
[amd64-shield]: https://img.shields.io/badge/amd64-yes-green.svg
[armhf-shield]: https://img.shields.io/badge/armhf-yes-green.svg
[armv7-shield]: https://img.shields.io/badge/armv7-yes-green.svg
[i386-shield]: https://img.shields.io/badge/i386-yes-green.svg
[issue]: https://github.com/home-assistant/addons-example/issues
