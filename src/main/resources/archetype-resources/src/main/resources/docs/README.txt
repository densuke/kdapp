= このデモについて

このデモパッケージは、今回のアーティファクトを使ったときのサンプルとなっています。

`DemoMySQL`
: MySQLを使ったデータベースアクセスサンプル、実行の際には事前準備が必要になります(後述)

`DemoDerby` ※pom.xmlにて有効にしないと作れません
: Derbyデータベースを使ったサンプルです

`DemoVE` ※pom.xmlにて有効にしないと作れません
: VisualEditorを使ったGUIのサンプルです

= 実行方法

デモの実行は、binディレクトリにあるバッチファイル(DemoMySQL.bat)を実行してください。UNIX/Linux環境では、binディレクトリにシェルスクリプト(DemoMySQL)がありますので、こちらをshに食わせてください。なお、諸事情により実行属性が付いていないため、必要なら自前で"`chmod +x`"してください。

= MySQLデモ実行の準備

MySQLを使ったデモの実行には、事前にデータベースを準備し、Win側からの接続を許可する必要があります。

1. `/etc/mysql/my.cnf` をチェックし、待ち受けが127.0.0.1のみであれば、全ポート待ち受けに変更して再起動してください
2. データベースsampleを作ってください
3. sampleに対し、sample@10.0.2.2から(Windows側は、VM側からこう見えている)の接続に対し、認証用パスワード'sample'で認可する

待ち受けポートを解放するためには、

    $ sudo vi /etc/mysql/my.cnf

とし、

    bind-address            = 127.0.0.1

の行を探してください。頭に"`#`"を付けてコメント状態にしましょう。

    # bind-address            = 127.0.0.1

保存して再起動をかけることで、待ち受けポートが解放されます。

    $ sudo service mysql restart

サンプルのデータベースを作成するにはLinux(VM上)のMySQLにPuTTYなどのコンソールでつないだ後、以下の操作を行ってください。

    > CREATE DATABASE IF NOT EXISTS sample; -- なければsample DBを作成
    > GRANT ALL PRIVILEGES ON sample.* to 'sample'@'10.0.2.2' IDENTIFIED BY 'sample'; 
    > FLUSH PRIVILEGES;

最後のFLUSH命令は実行しなくても動くみたいですが、念のためです(GRANTを即時有効にするための処理、通常はセッションを切れば有効になります)。

= デモのソースについて

各デモのソースは、パッケージ空間で分離しています。ソース自体はsrc/main/java以下に、"`${package}.derby`""`${package}.mysql`""`${package}.ve`"それぞれにクラスを配置しています。

= デモプログラムのビルド方法

DerbyやVE(VisualEditor)のデモを有効にしたい場合は、pom.xmlの中でコメントアウトしている部分を有効にしてからビルドし直してください。Eclipse上で行うときは、pom.xmlかプロジェクト名を右クリックし、"実行→Maven package"で行えます。コマンドライン上であれば、

    $ mvn package

で作成可能です。出来上がったパッケージはtargetディレクトリにzipアーカイブの形で作成されます。

