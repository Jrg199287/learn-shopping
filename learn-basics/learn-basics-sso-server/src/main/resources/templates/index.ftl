<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>统一认证中心</title>

    <#import "common/common.macro.ftl" as netCommon>
    <@netCommon.commonStyle />
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
        <!-- header -->
    <@netCommon.commonHeader />
        <!-- left -->
    <@netCommon.commonLeft "help" />
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>使用教程</h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="callout callout-success">

            </div>
        </section>
        <!-- /.content -->
        <section class="content">
            <div class="Service-box callout ">
                <div class="Service-content clearfix">
                    <a href="javascript:;" class="Service-item">
                        <div class="item-image">
                            <img src="./static/images/icon-tag001.png" alt="">
                        </div>
                        <h3 class="item-title">电脑网站支付</h3>
                        <div class="item-text">用户在商家网站上完成付款</div>
                        <span class="item-link">我要接入</span>
                    </a>
                    <a href="javascript:;" class="Service-item">
                        <div class="item-image">
                            <img src="./static/images/icon-tag002.png" alt="">
                        </div>
                        <h3 class="item-title">手机网站支付</h3>
                        <div class="item-text">用户在商家手机网站进行付款</div>
                        <span class="item-link">我要接入</span>
                    </a>
                    <a href="javascript:;" class="Service-item">
                        <div class="item-image">
                            <img src="./static/images/icon-tag003.png" alt="">
                        </div>
                        <h3 class="item-title">APP支付</h3>
                        <div class="item-text">用户在商家app内进行付款</div>
                        <span class="item-link">我要接入</span>
                    </a>
                    <a href="javascript:;" class="Service-item">
                        <div class="item-image">
                            <img src="./static/images/icon-tag004.png" alt="">
                        </div>
                        <h3 class="item-title">当面付</h3>
                        <div class="item-text">只需扫描用户的付款码，或用户扫描商家的二维码完成付款</div>
                        <span class="item-link">我要接入</span>
                    </a>
                    <a href="javascript:;" class="Service-item">
                        <div class="item-image">
                            <img src="./static/images/icon-tag005.png" alt="">
                        </div>
                        <h3 class="item-title">收钱码</h3>
                        <div class="item-text">支付宝最新收钱工具，收钱、提钱均免费</div>
                        <div class="item-background">
                            <img src="./static/images/icon-tag-bj.png" alt="">
                        </div>
                        <div class="item-tag">免手续费</div>
                        <span class="item-link">我要接入</span>
                    </a>
                </div>
            </div>
        </section>
    </div>
    <!-- /.content-wrapper -->

    <!-- footer -->
<@netCommon.commonFooter />
</div>
<@netCommon.commonScript />
</body>
</html>
