<!-- https://developers.home-assistant.io/docs/add-ons/presentation#keeping-a-changelog -->

## 1.5.1

- **Added ingress path detection and logging**: Automatically detects Home Assistant ingress path from addon environment
- **Improved debugging**: Logs full ingress URL and extracted path for troubleshooting
- **Dynamic URL root location**: Configures kiwix-serve with proper `--urlRootLocation` based on detected ingress path
- **Better error diagnosis**: Will help identify if ingress path extraction is working correctly

## 1.5.0

- **MAJOR APPROACH CHANGE: Configure kiwix-serve for ingress**: Added `--urlRootLocation` parameter to kiwix-serve itself
- **Simplified nginx to basic proxy**: Removed all complex path rewriting and location matching from nginx
- **Let kiwix handle ingress paths**: kiwix-serve now generates all URLs with correct ingress path prefix
- **Cleaner architecture**: nginx just proxies, kiwix-serve handles all URL generation and asset paths
- **Should fix all 404 asset errors**: kiwix-serve will generate correct URLs for all assets from the start

## 1.4.2

- **Fixed nginx service restart loop**: Removed problematic debug grep command that was failing and causing service restarts
- **Cleaner startup**: Simplified nginx service script to avoid restart loops from failed debug commands
- **Stable logging**: Kept essential ingress path logging but removed problematic configuration dump

## 1.4.1

- **CRITICAL FIX: Handle mixed ingress behavior**: Discovered HA handles some requests differently - some keep ingress path, others don't
- **Dual location blocks**: Added specific location for full ingress paths + fallback for stripped paths
- **Covers all cases**: viewer_settings.js (keeps path) vs skin/kiwix.css (stripped path) now both work
- **Complete ingress compatibility**: Finally addresses the inconsistent Home Assistant ingress path handling

## 1.4.0

- **BREAKTHROUGH: Fixed ingress completely**: Discovered Home Assistant already strips ingress paths - removed unnecessary rewrite rules
- **Simplified to direct proxy**: nginx now directly proxies requests to kiwix-serve without path manipulation
- **Root cause identified**: The 404s were caused by trying to strip paths that were already stripped by HA ingress
- **Clean configuration**: Removed all complex rewrite logic, keeping only essential sub_filter for HTML links
- **Major stability improvement**: Back to simple, reliable proxy configuration that actually works

## 1.3.9

- **Added ingress path debugging**: Enhanced nginx service script to log actual ingress paths and regex patterns
- **Fixed regex escaping**: Properly escape special characters in ingress paths for nginx rewrite rules
- **Debug logging**: Added detailed logging to troubleshoot ingress path replacement issues
- **Improved diagnostics**: Show actual nginx configuration after ingress path substitution

## 1.3.8

- **Fixed ingress path rewriting**: Switched from complex regex location matching to simple rewrite rules
- **Simplified nginx configuration**: Using reliable `rewrite` directive instead of problematic location regex with variables
- **Eliminated 404 errors**: All assets should now properly load by correctly stripping ingress path prefix
- **Improved reliability**: Back to proven nginx rewrite approach that works consistently

## 1.3.7

- **Fixed nginx configuration error**: Removed problematic `proxy_redirect default` directive that conflicts with variables in `proxy_pass`
- **Updated add-on logos**: Refreshed icon and logo images for better visual representation
- **Improved nginx compatibility**: Configuration now properly handles variable-based proxy passing without redirect conflicts

## 1.3.6

- **Complete ingress path handling overhaul**: Switched to regex-based location matching to properly strip ingress prefix
- **Universal asset proxying**: All assets now properly proxied regardless of path structure
- **Simplified sub_filter rules**: Reduced to essential HTML link rewriting only
- **Fixed 404 errors**: Eliminated asset loading failures by handling path stripping at nginx location level
- **Improved reliability**: Universal approach works with any Kiwix UI structure changes

## 1.3.5

- **Fixed CSS/JS loading in ingress**: Enhanced sub_filter rules to properly rewrite all asset URLs
- **Improved asset path handling**: Added comprehensive URL rewriting for /skin/, /viewer_settings.js, and other Kiwix assets
- **Better ingress compatibility**: Fixed plain HTML appearance by ensuring all resources load through ingress path
- **Enhanced sub_filter coverage**: Added support for single quotes, unquoted paths, and JavaScript-specific URLs

## 1.3.4

- **Fixed nginx permission issues**: Moved all nginx temp directories to /tmp/nginx/ (always writable in containers)
- **Resolved directory creation errors**: Eliminated permission denied errors when creating nginx directories
- **Simplified Dockerfile**: Removed unnecessary directory creation that was causing permission conflicts
- **Improved container compatibility**: Using standard /tmp paths that work across all Home Assistant base images

## 1.3.3

- **Fixed nginx directory creation issues**: Create all required nginx temp directories at build time and runtime
- **Fixed nginx error log path**: Ensure nginx can write to its expected log locations
- **Enhanced directory structure**: Pre-create complete directory structure nginx expects
- **Improved reliability**: Both build-time and runtime directory creation for maximum compatibility

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
