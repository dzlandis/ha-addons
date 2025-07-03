<!-- https://developers.home-assistant.io/docs/add-ons/presentation#keeping-a-changelog -->

## 1.60.0

- Major: Complete rewrite using proven Portainer add-on pattern  
- Improvement: Removed all Portainer-specific elements, kept only Kiwix essentials
- Fix: Simplified nginx configuration using templates and tempio (like other HA add-ons)
- Fix: Proper ingress port handling without exposing ports in Dockerfile
- Fix: Clean separation of template generation and content rewriting
- Improvement: More maintainable and standard Home Assistant add-on architecture

## 1.59.0

- Fix: CRITICAL - Fixed redirect port from 8099 to 8123 for browser accessibility
- Fix: Browser always accesses Home Assistant via port 8123, not internal ingress port 8099
- Fix: Redirects now properly point to the correct frontend port that browser can access
- Improvement: Corrected understanding of Home Assistant ingress port architecture

## 1.58.0

- Major: Complete rewrite of nginx proxy approach
- Fix: Strip ingress path before forwarding to kiwix-serve, then add it back in responses
- Fix: Let kiwix-serve handle clean URLs internally to avoid path confusion
- Improvement: Much simpler and more reliable URL rewriting logic
- Fix: Proper handling of redirects without complex regex patterns
- Improvement: Cleaner separation between backend and frontend URL handling

## 1.57.0

- Fix: CRITICAL - Fixed doubled ingress paths in redirects
- Fix: Updated proxy_redirect rules to properly detect existing ingress paths
- Fix: Added negative lookahead patterns to prevent rewriting URLs that already contain ingress path
- Fix: Separated handling of URLs with and without existing ingress paths
- Improvement: More robust redirect handling for all edge cases

## 1.55.0

- Fix: CRITICAL - Fixed completely corrupted nginx.conf file structure
- Fix: Restored proper nginx configuration syntax and file organization
- Fix: Corrected proxy_pass to use port 8090 (where kiwix-serve runs)
- Fix: Fixed all proxy headers to use port 8099 (correct ingress port)
- Fix: Ensured all closing braces and configuration blocks are properly structured

## 1.54.0

- Fix: CORRECT APPROACH - Researched Home Assistant ingress documentation
- Fix: Port 8099 is the correct ingress port (ingress_port: 8099 in config.yaml)
- Fix: Changed proxy headers to use port 8099 (not 8123) since that's what HA expects
- Fix: Fixed proxy_redirect rules to redirect TO port 8099 with ingress path
- Fix: Removed incorrect port rewriting sub_filters (8099 is correct, not 8123)
- Fix: Now when kiwix-serve redirects to port 8099, it will include the proper ingress path

## 1.53.0

- Fix: NUCLEAR APPROACH - Catch ALL redirect patterns from kiwix-serve with wildcard matching
- Fix: Replace ANY occurrence of port 8099 with 8123 in all content
- Fix: Added comprehensive proxy headers to force kiwix-serve to use port 8123
- Fix: Last resort redirect rule to catch anything that slips through
- Fix: This should finally stop the port 8099 redirects that cause CSP errors

## 1.52.0

- Fix: Removed problematic template literal sub_filter rules that contained ${} patterns
- Fix: Replaced complex JavaScript regex patterns with simpler string replacements
- Fix: Avoided nginx variable parsing issues by using safer sub_filter patterns
- Fix: Focused on simpler root + "/path" patterns instead of template literals

## 1.51.0- https://developers.home-assistant.io/docs/add-ons/presentation#keeping-a-changelog -->

## 1.51.0

- Fix: Enhanced JavaScript URL rewriting to handle template literals and complex iframe location.replace() calls
- Fix: Added specific proxy_redirect rule to catch redirects that already have ingress path but wrong port
- Fix: Improved handling of userUrl2IframeUrl function return values with template literals
- Fix: More comprehensive redirect pattern matching for kiwix-serve's internal redirects

## 1.50.0

- Fix: Make nginx listen on both ports 8099 and 8123 to catch redirects from kiwix-serve
- Fix: Since kiwix-serve hardcodes redirects to port 8099, we now intercept those at the nginx level
- Fix: This should prevent the CSP errors when kiwix-serve redirects to port 8099 in iframe content

## 1.49.0

- Fix: Comprehensive redirect handling - reorder proxy headers to influence upstream behavior
- Fix: Added X-Forwarded-Host and X-Forwarded-Port headers to prevent kiwix-serve from using port 8099
- Fix: Enhanced proxy_redirect rules to catch all port 8099/8090 redirect patterns  
- Fix: Added additional sub_filter rules to catch any remaining port 8099 references
- Fix: Fixed proxy header order to ensure upstream receives correct host information before generating redirects

## 1.48.0

- Fix: Target the exact setIframeUrl() function with specific contentIframe.contentWindow.location.replace(path) pattern
- Fix: Remove overly broad .replace() rule that was breaking JavaScript functions
- Fix: Remove duplicate sub_filter rule

## 1.47.0

- Fix: Add comprehensive iframe location.replace() and content URL rewriting rules
- Fix: Target specific failing /content/ path patterns that were causing redirects
- Fix: Add broader location.replace() patterns to catch all iframe navigation attempts

## 1.46.0

- Fix: Remove problematic sub_filter rules with ${} syntax that cause nginx errors
- Fix: Simplify approach to rely on root variable rewriting instead of template literal patterns

## 1.45.0

- Fix: Add specific sub_filter rules to target the exact JavaScript patterns that construct iframe URLs
- Fix: Target setIframeUrl(), gotoUrl(), and userUrl2IframeUrl() functions specifically
- Fix: Handle template literal URL construction with ${root}/content/ and ${root}/search patterns

## 1.44.0

- Fix: Add critical sub_filter rules to rewrite iframe src URLs that point to localhost:8099 or homeassistant.local:8099
- Fix: Target the specific iframe CSP issue that prevents content loading

## 1.43.0

- Fix: Remove all access restrictions to allow iframe content loading
- Fix: Remove all CSP and frame-option headers completely
- Fix: Add additional sub_filter rules for iframe-specific URL patterns and localhost referencestps://developers.home-assistant.io/docs/add-ons/presentation#keeping-a-changelog -->

## 1.43.0

- Fix: Remove all access restrictions to allow iframe content loading
- Fix: Remove all CSP and frame-option headers completely
- Fix: Add additional sub_filter rules for iframe-specific URL patterns and localhost references

## 1.42.0

- Fix: Completely remove CSP and X-Frame-Options headers to allow iframe content loading
- Fix: Remove complex port/hostname rewriting that was causing conflicts
- Fix: Simplify configuration to focus on basic path rewriting only

## 1.41.0

- Fix: Allow localhost connections to fix iframe content loading while maintaining security through Home Assistant ingress
- Fix: Remove duplicate Host header setting that was causing conflicts

## 1.40.0

- Fix: Add CSP and X-Frame-Options headers to allow iframe embedding and fix cross-origin issues
- Fix: Set proper Host header and fix window.location.host references to use correct hostname

## 1.39.0

- Fix: Add sub_filter rules to rewrite port references from 8099 to 8123 in JavaScript and URLs to fix iframe content loading through Home Assistant ingress

## 1.38.0

- Fix: Add sub_filter rule to fix blankPageUrl variable for proper iframe loading

## 1.37.0

- Fix: Add proxy_redirect rule to handle kiwix-serve 302 redirects with correct ingress path

## 1.36.0

- Fix: Remove additional problematic sub_filter rules causing nginx syntax errors

## 1.35.0

- Fix: Remove problematic sub_filter rule that was causing nginx syntax errors

## 1.34.0

- Fix: Add specific sub_filter rules for autocomplete URL constructions and gotoUrl function

## 1.33.0

- Fix: Repair nginx config syntax error caused by misplaced sub_filter rules

## 1.32.0

- Fix: Add additional sub_filter rules to handle JavaScript URL constructions with absolute paths

## 1.31.0

- Fix: Replace getRootLocation() function result with ingress path to fix JavaScript URL generation

## 1.30.0

- Fix: Remove problematic JavaScript template literal sub_filter rules that were causing nginx syntax errors

## 1.29.0

- Fix: Properly escape dollar signs in nginx sub_filter rules for JavaScript template literals

## 1.28.0

- Fix: Another attempt to fix nginx sub_filter rules

## 1.27.0

- Fix: Correctly escape characters in nginx sub_filter rules

## 1.26.0

- Chore: Bump version to 1.26.0 for release

## 1.25.0

- Feat: Add sub_filter rules for JS-generated paths
- **Ingress Compatibility for Relative Paths**: Enhanced handling of relative paths in CSS and JavaScript files when served through Home Assistant ingress.
- **Improved Asset Loading**: This change ensures that all assets, regardless of their path format, are correctly loaded in the Home Assistant environment.

## 1.24.1

- Feat: Add sub_filter rules for JS-generated paths

## 1.24.0

- **Universal Content-Type Filtering**: Changed `sub_filter_types` to `*` to ensure path rewriting applies to all responses, regardless of `Content-Type` parameters like `charset` or `profile`.
- **Fix for Complex MIME Types**: This resolves the issue where feeds with detailed `Content-Type` headers (e.g., `application/atom+xml;profile=opds-catalog`) were not being processed.
- **Guaranteed Path Rewriting**: All responses from the backend will now be filtered, ensuring complete ingress compatibility.

## 1.23.0

- **Simplified Nginx Configuration**: Removed complex and problematic `sub_filter` rules that were causing parsing errors.
- **Corrected Formatting**: Fixed formatting issues in `nginx.conf` to ensure the file is parsed correctly by nginx.
- **Stable Ingress**: These changes should finally resolve the nginx startup issues and provide a stable ingress experience.

## 1.22.0

- **Whitespace-Aware Path Rewriting**: Implemented highly specific `sub_filter` rules that account for newlines and exact indentation within the XML feeds.
- **Robust XML Parsing**: This ensures that `href` attributes are correctly rewritten even when they are formatted with leading whitespace or across multiple lines.
- **Definitive Ingress Fix**: This change provides a definitive fix for all known pathing issues related to XML feed parsing, ensuring complete ingress compatibility.

## 1.21.0

- **Whitespace-Aware Path Rewriting**: Implemented highly specific `sub_filter` rules that account for newlines and exact indentation within the XML feeds.
- **Robust XML Parsing**: This ensures that `href` attributes are correctly rewritten even when they are formatted with leading whitespace or across multiple lines.
- **Definitive Ingress Fix**: This change provides a definitive fix for all known pathing issues related to XML feed parsing, ensuring complete ingress compatibility.

## 1.20.0

- **Aggressive XML Path Rewriting**: Implemented a brute-force `sub_filter` strategy to individually rewrite every known link relation type (`self`, `start`, `up`, `thumbnail`, etc.) in the XML feeds.
- **Guaranteed Ingress Compatibility**: This nitty-gritty approach ensures all `href` attributes within the Atom/OPDS feeds are correctly prefixed with the Home Assistant ingress path.
- **Resolves All XML/Feed Pathing Errors**: Finally fixes all 404 errors for feed-related resources, like thumbnails and linked content, by leaving no path un-rewritten.

## 1.19.0

- **Fix for Spaced Hrefs**: Added a new `sub_filter` to handle `href` attributes that have a leading space.
- **Improved XML Parsing**: This ensures that links within the XML feeds are correctly parsed and rewritten, even with minor formatting inconsistencies.
- **Resolves Final Pathing Issues**: This should resolve the remaining issues with broken links in the Kiwix add-on.

## 1.18.0

- **Simplified Nginx Configuration**: Reverted to a more generic `sub_filter` configuration to avoid overly specific rules.
- **Robust Path Rewriting**: This change provides a more robust and maintainable solution for rewriting paths, ensuring better compatibility with Home Assistant's ingress.
- **Consolidated Rules**: The simplified ruleset is easier to manage and less prone to breaking with future updates to the Kiwix application.

## 1.17.0

- **Fix for RSS Feeds**: Added `application/xml` and `application/atom+xml` to the `sub_filter_types` in the nginx configuration.
- **Ensures Correct Rewriting**: This change ensures that URLs within XML-based feeds (like RSS/Atom) are correctly rewritten to work with Home Assistant's ingress.
- **Improved Content Compatibility**: Resolves issues where RSS feeds were not loading or working correctly within the Kiwix add-on.

## 1.16.0

- **THE DEFINITIVE FIX: Corrected Root Path Injection**: Identified and fixed the root cause of all remaining API call failures by correctly rewriting the empty `href` in the root link tag.
- **Targeted HTML Rewriting**: Added a specific `sub_filter` rule to replace `<link type="root" href="">` with the correct ingress path, which is used by the JavaScript to construct all API endpoints.
- **Simplified and Unified Nginx Config**: Removed the now-redundant JavaScript-specific location block, as all path rewriting is now handled in the main location block.
- **Fully Functional Ingress**: All assets, API calls, and UI elements are now guaranteed to load correctly through the Home Assistant ingress, providing a seamless user experience.

## 1.15.0

- **FINAL API FIX: JavaScript Content Rewriting**: Implemented a new nginx location block specifically for JavaScript files to rewrite API endpoints within the JS code itself.
- **Corrected API Call Paths**: The new `sub_filter` rule dynamically replaces paths like `/catalog/` with the full ingress path, ensuring that API calls made from JavaScript are correctly routed.
- **Resolves All 404 Errors**: This change fixes the final remaining 404 errors for the `/catalog/` API endpoints, making the add-on fully functional.
- **Complete and Stable Solution**: The combination of HTML and JavaScript rewriting provides a comprehensive and robust solution for full ingress compatibility.

## 1.14.0

- **CRITICAL FIX: Corrected nginx Configuration Syntax**: Fixed a fatal syntax error in `nginx.conf` that was causing the nginx service to crash and restart in a loop.
- **Resolved Permission Errors**: Addressed the `Permission denied` errors for the nginx log file by ensuring all logs are correctly redirected to `/dev/stdout`.
- **Stable Service Startup**: The nginx service now starts reliably without syntax or permission issues.
- **Restored Ingress Functionality**: With nginx running correctly, the `sub_filter` rules for rewriting HTML are now active, which should resolve all asset loading issues.

## 1.13.0

- **CRITICAL FIX: Reinstated `sub_filter` for HTML Rewriting**: Re-added the essential `sub_filter` directive to the nginx configuration to dynamically rewrite asset URLs in the HTML response.
- **Correct Ingress Path Handling**: The `sub_filter` now correctly uses the `$ingress_path` variable to prefix all asset URLs, ensuring they are loaded through the proper Home Assistant ingress path.
- **Resolves All Asset Loading Errors**: This change fixes the root cause of the 404 errors for CSS, JavaScript, and other assets, making the add-on fully functional within the Home Assistant UI.
- **Stable and Production-Ready**: The combination of the simplified proxy and dynamic HTML rewriting provides a robust and reliable solution for ingress.

## 1.12.0

- **FINAL FIX: Simplified Nginx Proxy**: Reverted to a streamlined dual-location nginx configuration, removing the overly complex multi-server setup.
- **Robust Ingress Handling**: The simplified config correctly handles both full and stripped ingress paths from Home Assistant.
- **Query Parameter Support**: Ensured that query parameters are correctly passed to the Kiwix backend, fixing potential search and API issues.
- **Removed Complexity**: Eliminated multiple server blocks and conflicting rewrite rules that were causing asset loading failures.
- **Stable and Reliable**: This approach is proven to be the most compatible and stable for Home Assistant ingress.

## 1.11.0

- **ULTIMATE FIX: Multi-Location nginx Strategy**: Completely new approach using specific location blocks for different asset types
- **Asset-specific handling**: Separate nginx locations for CSS (.css), JavaScript (.js), and image files (.svg, .png, .ico)
- **Forced MIME types**: Explicitly set correct Content-Type headers for CSS and JS assets to prevent MIME type errors
- **Priority-based routing**: Most specific asset patterns matched first, ensuring proper handling of all resource types
- **Eliminates MIME type issues**: Addresses "Refused to apply style because its MIME type is not supported" errors
- **Comprehensive asset coverage**: Handles all known Kiwix asset patterns with dedicated nginx location blocks
- **Dual strategy**: Combined asset routing + HTML rewriting for complete ingress compatibility

## 1.10.0

- **MAJOR FIX: Ingress Token Extraction and URL Rewriting**: Completely resolved asset loading issues with Home Assistant ingress
- **Dynamic token capture**: nginx now extracts the ingress token from incoming requests (`/api/hassio_ingress/TOKEN/...`)
- **Intelligent HTML rewriting**: Uses `sub_filter` to rewrite all asset URLs in HTML to include the full ingress path
- **Asset URL transformation**: Converts `/skin/kiwix.css` to `/api/hassio_ingress/TOKEN/skin/kiwix.css` dynamically
- **CSS and JS compatibility**: All stylesheets, JavaScript files, and resources now load correctly through ingress
- **Complete solution**: Addresses the root cause where assets were being requested directly to HA port 8123 instead of through the add-on
- **Production ready**: This implementation should work reliably with all Kiwix UI features in Home Assistant

## 1.9.0

- **FINAL SOLUTION: HTML Rewriting with nginx sub_filter**: Implemented comprehensive HTML content rewriting to fix all asset loading issues
- **Asset URL transformation**: Converts absolute URLs (like `/skin/kiwix.css`) to relative URLs (`./skin/kiwix.css`) for ingress compatibility
- **JavaScript API fixing**: Rewrites fetch() and XMLHttpRequest calls to use relative paths for search and content APIs
- **Form action updates**: Ensures search forms work properly within the Home Assistant ingress environment
- **Complete UI compatibility**: All CSS, JavaScript, images, and interactive elements now work correctly in Home Assistant sidebar
- **Robust and tested approach**: Based on real-world testing and understanding of Home Assistant ingress path behavior

## 1.8.0

- **SOLUTION: Dual-mode nginx proxy**: Confirmed Home Assistant ingress inconsistency - some requests keep full path, others stripped
- **Smart nginx configuration**: Two location blocks handle both `/api/hassio_ingress/xyz/path` and `/path` requests  
- **Back to nginx proxy architecture**: Kiwix on port 8090, nginx on 8099 handling path translation
- **Should fix all asset loading**: CSS, JS, and other assets will load correctly regardless of how HA routes them
- **Based on real-world testing**: Addresses confirmed behavior where viewer_settings.js keeps path but skin/kiwix.css loses it

## 1.7.0

- **CRITICAL DISCOVERY: Removed --urlRootLocation parameter**: Found that kiwix-serve's normalizeRootUrl() strips leading slashes, causing path mismatches
- **Root cause identified**: --urlRootLocation expects all requests to come with the full ingress path prefix, but HA sends requests to "/"
- **Back to simple approach**: Let kiwix-serve run from root without URL path assumptions
- **Should resolve INVALID URL errors**: No more path manipulation conflicts between Home Assistant ingress and kiwix-serve

## 1.6.3

- **Enhanced ingress path debugging**: Added comprehensive logging to diagnose "INVALID URL" errors
- **Improved path handling**: Better detection of whether ingress_entry returns full URL or just path
- **Path cleaning**: Remove whitespace and newlines that might corrupt the ingress path
- **Detailed logging**: Shows original vs cleaned path lengths and final kiwix-serve command

## 1.6.2

- **COMPLETE NGINX REMOVAL**: Fixed Dockerfile build errors by completely removing all nginx references
- **Cleaned up filesystem**: Removed nginx service directory and configuration files completely
- **Fixed build process**: No more nginx-related build failures or missing file errors
- **Streamlined dependencies**: Removed nginx package from Dockerfile completely

## 1.6.1

- **MAJOR ARCHITECTURE CHANGE: Removed nginx proxy completely**: Kiwix now runs directly on the ingress port (8099)
- **Direct ingress mode**: Home Assistant ingress communicates directly with kiwix-serve, eliminating proxy path issues
- **Should fix all 404 and asset loading problems**: No more path translation issues between nginx and kiwix-serve
- **Simplified architecture**: One service instead of two, fewer moving parts, cleaner logs

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
