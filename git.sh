while true
do
  sleep 600
  echo 'IchTuNixÄnderung ' >> changed.txt
  git add -A *
  git commit -a --message=AutoPush --author="ghostbrine_ <ghostbrine@gmx.de>"
  git push -u main master
done