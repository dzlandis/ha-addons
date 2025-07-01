<!-- https://developers.home-assistant.io/docs/add-ons/presentation#keeping-a-changelog -->

## 1.3.0

- **Simplified ingress implementation**: Using proper Home Assistant ingress specification
- **Security enhancement**: Access only via Home Assistant ingress (port 8099)
- Follows official Home Assistant ingress requirements (172.30.32.2 IP restriction)
- Removed complex nginx reverse proxy in favor of native kiwix-serve ingress support
- Improved reliability and reduced complexity

## 1.2.0

- **Fixed ingress compatibility**: Resolved CSS/JS loading issues when accessing via Home Assistant sidebar
- Added URL root location configuration for proper asset loading in ingress mode
- Enhanced debugging for ingress-related issues

## 1.1.0

- **Added sidebar integration**: Kiwix now appears in the Home Assistant sidebar for easy access
- Enabled ingress support for seamless Home Assistant integration
- Updated documentation to reflect sidebar access

## 1.0.0

- Initial release of Kiwix Home Assistant Add-on
- **Uses official Kiwix Docker image** for reliable, up-to-date kiwix-serve binary
- Support for serving ZIM files through Kiwix server
- Configurable library path and port
- Automatic detection and serving of entire ZIM directory (no need to specify individual files)
- Multi-threaded performance optimization (configurable 1-16 threads)
- Configurable search limit for multi-ZIM searches
- Security option to block external links
- Interface customization options (hide/show search bar and library button)
- Welcome page when no content is available with helpful setup instructions
- Full multi-architecture support (aarch64, amd64, armhf, armv7, i386)
- Integration with Home Assistant file system (share, media, config folders)
- Built-in ZIM management helper tool (`kiwix-helper`)
- Comprehensive logging and error handling
- AppArmor security profile for enhanced security
