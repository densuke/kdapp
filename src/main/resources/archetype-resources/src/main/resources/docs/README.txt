= このデモについて

このデモパッケージは、今回のアーティファクトを使ったときのサンプルとなっています。
デモは3つ入っています。

`DemoDerby`
: Apache Derbyを使ったデータベースアクセスサンプル

`DemoMySQL`
: MySQLを使ったデータベースアクセスサンプル、実行の際には事前準備が必要になります(後述)

`DemoVE`
: VisualEditorを使ったGUIのサンプル

= 実行方法

各デモの実行は、binディレクトリにある各バッチファイルを実行してください。
UNIX/Linux環境では、binディレクトリにシェルスクリプトがありますので、こちらをshに食わせてください。

= MySQLデモ実行の準備

MySQLを使ったデモの実行には、事前にデータベースを準備し、Win側からの接続を許可する必要があります。

1. データベースsampleを作る
2. sampleに対し、sample@10.0.2.2から(Windows側は、VM側からこう見えている)の接続に対し、認証用パスワード'sample'で認可する

具体的にはLinux(VM上)のMySQLにPuTTYなどのコンソールでつないだ後、以下の操作を行ってください。

    > CREATE DATABASE sample;
    > GRANT ALL PRIVILEGES ON sample.* to 'sample'@'10.0.2.2' IDENTIFIED BY 'sample';
    > FLUSH PRIVILEGES;

最後のFLUSH命令は実行しなくても動くみたいですが、念のためです(GRANTを即時有効にするための処理、通常はセッションを切れば有効になります)。

= 各デモのソースについて

各デモのソースは、パッケージ空間で分離しています。
ソース自体はsrc/main/java以下に、"`${package}.derby`""`${package}.mysql`""`${package}.ve`"それぞれにクラスを配置しています。
