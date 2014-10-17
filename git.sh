while true
do
  echo 'IchTuNixÄnderung ' >> changed.txt
  git add -A *
  git commit -a --message=AutoPush --author="ghostbrine_ <ghostbrine@gmx.de>"
  push -u test master
  sleep 30
done