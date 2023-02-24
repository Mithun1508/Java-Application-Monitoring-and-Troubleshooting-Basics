Playbook for production hosts
=============================

Setup provisioning admin account
--------------------------------
`iaac/inventories/production/hosts.yml`

Smoke test Ansible connection
-----------------------------
```cygwin shell
ansible -i inventories/production -m shell -a 'uname -a' all --ask-pass --ask-become-pass
ansible -i inventories/production -m shell -a 'cat /etc/os-release' all --ask-pass --ask-become-pass 
```

Run playbook against production hosts inventory
-----------------------------------------------
```cygwin shell
ansible-playbook site.yml -i inventories/production --ask-pass --ask-become-pass
```
