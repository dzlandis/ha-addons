# Home Assistant Add-on: Kiwix

## How to use

This add-on provides an offline content reader for educational materials like Wikipedia, accessible through ZIM files. **The add-on integrates seamlessly with Home Assistant and appears in the sidebar for easy access.**

### Getting Started

1. **Download ZIM files**: Visit [library.kiwix.org](https://library.kiwix.org/) to download content
2. **Upload files**: Place your ZIM files in the configured library path (default: `/share/kiwix/`)
3. **Start the add-on**: The service will automatically detect and serve your content
4. **Access content**: 
   - **Via sidebar**: Click "Kiwix" in the Home Assistant sidebar (recommended)
   - **Direct access**: Open `http://homeassistant.local:8080` in your browser

### Configuration Options

#### Log Level
Set the verbosity of logs:
- `trace`: Maximum detail
- `debug`: Debug information
- `info`: General information (default)
- `notice`: Important notices
- `warning`: Warning messages
- `error`: Error messages only
- `fatal`: Critical errors only

#### Library Path
Specify where your ZIM files are stored. The path must be accessible from within the Home Assistant environment.

**Popular paths:**
- `/share/kiwix/` (default) - Files stored in Home Assistant's share folder
- `/media/kiwix/` - Files stored in media folder
- `/config/kiwix/` - Files stored in config folder

#### Port
The port number where Kiwix will serve content. Default is 8080.

#### Threads
Number of threads to run in parallel (1-16). Higher values can improve performance with multiple simultaneous users. Default is 4.

#### Search Limit
Maximum number of ZIM files in a fulltext multi-ZIM search. Set to 0 for no limit (0-100). Default is 0.

#### Block External Links
Prevent users from directly navigating to external resources via links in ZIM content. Useful for security in restricted environments. Default is false.

#### Hide Search Bar
Hide the search box in the ZIM viewer toolbar. Useful if you want to simplify the interface. Default is false.

#### Hide Library Button
Hide the library home button in the ZIM viewer toolbar. Useful if serving only a single ZIM file. Default is false.

### ZIM Files

ZIM files are compressed archives containing offline content. Popular options include:

- **Wikipedia**: Complete Wikipedia dumps in various languages
- **Wiktionary**: Multilingual dictionary and thesaurus
- **TED Talks**: Educational talks with transcripts
- **Project Gutenberg**: Collection of free ebooks
- **Stack Overflow**: Programming questions and answers

### File Management

ZIM files can be large (several GB). Ensure you have sufficient storage space:

1. Check available space in Home Assistant
2. Consider storing files on external storage if needed
3. Remove unused ZIM files to free up space

#### ZIM Management Helper

The add-on includes a built-in helper script for managing ZIM files. You can access it through the Home Assistant SSH add-on or terminal:

```bash
# List all ZIM files
kiwix-helper list

# Show information about a specific ZIM file
kiwix-helper info wikipedia_en_all_2023-01.zim

# Show total size of all ZIM files
kiwix-helper size

# Check for broken or invalid ZIM files
kiwix-helper check

# Show help
kiwix-helper help
```

### Sidebar Integration

The Kiwix add-on integrates seamlessly with Home Assistant through the ingress feature. Once the add-on is running, you'll see a "Kiwix" entry in your Home Assistant sidebar. This provides:

- **Easy access**: No need to remember ports or URLs
- **Seamless integration**: Works with Home Assistant authentication
- **Consistent experience**: Matches the look and feel of other Home Assistant interfaces

The sidebar entry uses a book icon (📖) and is accessible to all users (not just administrators).

### Troubleshooting

**No content visible?**
- Ensure ZIM files are in the correct directory
- Check that file extensions are `.zim`
- Verify file permissions allow reading
- Restart the add-on after adding new files

**Cannot access the web interface?**
- **Sidebar integration**: Look for "Kiwix" in your Home Assistant sidebar
- Verify the port is not blocked by firewall (for direct access)
- Check if another service is using the same port
- Review add-on logs for error messages

**Interface looks broken in sidebar?**
- This usually indicates CSS/JS loading issues in ingress mode
- Check the add-on logs for "URL root location" messages
- Try restarting the add-on
- If issues persist, try accessing via direct URL first, then sidebar

**Add-on won't start?**
- Check configuration syntax
- Ensure library path exists and is accessible
- Review Home Assistant supervisor logs
