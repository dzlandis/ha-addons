<!-- https://developers.home-assistant.io/docs/add-ons/presentation#keeping-a-changelog -->

## 1.3.2

- **Fixed nginx user permission issue**: Added explicit `user root;` directive to nginx configuration
- **Simplified nginx configuration**: Removed complex temp directory configurations that were causing permission conflicts
- **Improved startup**: nginx now starts without attempting problematic directory ownership changes

## 1.3.1

- **Fixed nginx permission error**: Resolved "Operation not permitted" error for nginx temp directories
- **Enhanced directory creation**: Pre-create all necessary nginx temp directories with proper permissions
- **Improved startup reliability**: Runtime directory creation ensures nginx starts correctly

## 1.3.0

- **Fixed ingress support**: Implemented proper nginx reverse proxy based on official Home Assistant add-on patterns
- **Security enhancement**: Added IP restriction (172.30.32.2) for ingress-only access
- **Path handling**: Proper ingress path prefix handling using template replacement pattern
- **Reliability**: Based on proven deCONZ add-on ingress implementation
- **Performance**: nginx reverse proxy provides better performance and reliability for ingress access

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
