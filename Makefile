# Makefile for code of article introduction to YAML

include ~/.make/color.mk
include ~/.make/python.mk
include ~/.make/git.mk

PYTHON_MOD=python

article: # Generate articles
	@echo "$(YEL)Generating articles$(END)"
	@mkdir -p $(BUILD_DIR)
	$(PYTHON) generate.py introduction-yaml.md > $(BUILD_DIR)/introduction-yaml.md
	$(PYTHON) generate.py introduction-yaml.en.md > $(BUILD_DIR)/introduction-yaml.en.md
