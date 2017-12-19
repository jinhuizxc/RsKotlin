package com.xyz.leeeyou.zhihuribao.utils;

import java.util.List;

/**
 * Created by diff on 2016/2/4.
 */

/**
 * storyDetail =StoryDetail(body=<div class="main-wrap content-wrap">
 <div class="headline">

 <div class="img-place-holder"></div>



 </div>

 <div class="content-inner">




 <div class="question">
 <h2 class="question-title"></h2>

 <div class="answer">

 <div class="content">
 <p style="text-align: center;">* * *</p>
 <p style="text-align: center;">天上人间 如果真值得歌颂</p>
 <p style="text-align: center;">也是因为有你</p>
 <p style="text-align: center;">才会变得闹哄哄</p>
 <p style="text-align: center;">* * *</p>
 </div>
 </div>


 </div>





 <div class="question">
 <h2 class="question-title">你曾经被哪些事情弄的莫名感动，那种情愫还难以名状？</h2>

 <div class="answer">

 <div class="meta">
 <img class="avatar" src="http://pic1.zhimg.com/da8e974dc_is.jpg">
 <span class="author">陈子勇</span>
 </div>

 <div class="content">
 <p>教我们宇航概括的老师，七十多岁了，周三晚上给我们上三个小时的课，有时候他的那台老诺基亚响铃以后 他也不接只是挂断，直到有一次才告诉我们，他老伴在医院生病很不好，响铃就代表着一切都还好～～～当时全班鼓掌～</p>
 </div>
 </div>

 <div class="answer">

 <div class="meta">
 <img class="avatar" src="http://pic2.zhimg.com/6913f4c1d_is.jpg">
 <span class="author">孟卓拉姆，</span><span class="bio">独步边疆+看书写字养娃儿</span>
 </div>

 <div class="content">
 <p>2004 年，怒江知子罗：路遇一基督徒的傈僳族少年，除了基督教从来不知还有其它宗教存在。我们谈各自的信仰，他非常开心地说：＂你是个好人，你是信主的道理的，我们在各自的信仰中做个好人。＂后来我在地里和当地人一起劳作，抬头时突然发现少年满眼泪水：＂你不信主你会下地狱的......以后我再也见不到你了......＂</p>
 <p>我人生至今最大的感动于此；少年人全部的真诚与朦胧的爱情不过如此。</p>
 </div>
 </div>

 <div class="answer">

 <div class="meta">
 <img class="avatar" src="http://pic4.zhimg.com/eca8f979f_is.jpg">
 <span class="author">崔望，</span><span class="bio">你们只是为了听故事</span>
 </div>

 <div class="content">
 <p>5 年前，跟父母一起去看望一个伯伯，姓纪，对我家有恩。</p>
 <p>纪伯伯患的是肌无力。当我们在唐都医院看见他时，那时他已经卧床很久，全身肌肉都已经坏死，不能动。唯一能动的肌肉只有眼皮。就这样躺在床上，生活完全不能自理，靠纪阿姨和护工照料。</p>
 <p>很难想象一个不能说话，不能动的人，是怎么样交流的吧。纪阿姨有一块小黑板，正面写着 26 个字母，一侧是声母,(b,c,d,s,sh 这些)，一侧是韵母（a,ai,ang,eng 这些）；反面写着常用的短语：吃饭，尿尿，拉屎，翻身…… 纪伯伯有需要的时候，就会眨眼睛，纪阿姨就用手指头在黑板上指，指到正确的，纪伯伯就眨眼睛。靠这样的方式进行交流。</p>
 <p>当你能呼吸，能听见，能看到这个世界的一切，只是没有办法动哪怕一丁点的时候，我不能想象那是多么的绝望。</p>
 <p>我们一家在病房里坐了有快 2 个小时，那时差不多下午 5 点，夕阳的余晖照进病房里。很安静的感觉。爸妈跟纪阿姨说着家常，说到了我考上了大学，高考成绩还不错。那整个过程中，我能感觉到纪伯伯一直在很用心的听。然后我看到纪伯伯眨眼了。</p>
 <p>我告诉纪阿姨，阿姨拿出小黑板，说正面（有字母那面）？</p>
 <p>纪伯伯：眨眼</p>
 <p>纪阿姨：a?b?c?是 c 对吧？</p>
 <p>纪伯伯：眨眼</p>
 <p>纪阿姨：a?ai?an?……u 是吧？崔是吧？</p>
 <p>纪伯伯：眨眼</p>
 <p>纪阿姨：崔？崔天时（我爸名字）？崔望？崔望是吧？</p>
 <p>纪伯伯：眨眼</p>
 <p>纪阿姨：然后呢？a?b?c?……s?</p>
 <p>纪伯伯：眨眼</p>
 <p>纪阿姨：有没有 h？有？shi 是吧？</p>
 <p>纪
 */
public class HtmlUtils {
    public static String structHtml(String oriStr, List<String> cssList) {
        StringBuilder htmlString = new StringBuilder("<html><head>");
        for (String css : cssList) {
            htmlString.append(structCssLink(css));
        }
        htmlString.append("</head><body>");
        htmlString.append("<style>img{max-width:340px !important;}</style>");
        htmlString.append(oriStr);
        htmlString.append("</body></html>");
        return htmlString.toString();
    }

    public static String structCssLink(String css) {
        return "<link type=\\\"text/css\\\" rel=\\\"stylesheet\\\" href=\\\"" + css + "\">";
    }
}
