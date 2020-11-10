 // 设置访问地址的公共部分
axios.defaults.baseURL="http://localhost:8080/Book";
// 设置响应拦截器，将响应的数据直接返回
axios.interceptors.response.use(function(res){
    return res.data;
}, function(error){
    console.log(error);  // 输出错误信息
});
// 时间格式化
Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
    "M+": this.getMonth() + 1, //月份 
    "d+": this.getDate(), //日 
    "H+": this.getHours(), //小时 
    "m+": this.getMinutes(), //分 
    "s+": this.getSeconds(), //秒 
    "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
    "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

var v = new Vue({
    el:'#app',
    data:{
        idflag: true,  // 编号栏不能操作
        nameflag:false,
        name:'',  
        na:'',  // 查询框的名字，防止和添加删除框重合
        book:{
            id:'',
            name:'',
            time:''
        },
        books:[],
        pageNum:1, // 当前页
        total:'', // 总记录数
        pages:'' // 总页数
    },
    methods:{
        add(){
            this.book.name = this.name;
            // 修改操作
            if(this.book.id != ''){
                axios.post('editBookById',{
                    id:this.book.id,
                    name:this.name
                }).then(ret => {
                    if(ret.success == true){
                        this.findBooks();
                    }else{
                        alert(ret.message);
                    }
                })
            // 添加操作
            }else{
                axios.post('addBook',this.book).then(ret => {
                    if(ret.success == true){
                        // 如果添加成功，刷新整个数据
                        this.findBooks();
                    }else{
                        // 添加失败，弹窗提醒
                        alert(ret.message);
                    }
                })
            }
            // 添加或者是修改后将编辑栏制空
            this.book={
                id:'',
                name:'',
                time:''
            };
            this.name = '';
            this.nameflag = false;
        },
        toEdit(id){
            // 首先通过id先查出对应的书本信息
            axios.get('findBookById?id='+id).then(ret => {
                var bo = ret;
                this.book.id = bo.id;
                this.book.name = bo.name;
                this.name = bo.name;
                this.book.time = bo.time;
            })
        },
        // 删除图书
        del(id){
            axios.get('deleteBookById?id='+id).then(ret => {
                if(ret.success == true){
                    // 刷新数据
                    this.findBooks();
                }else{
                    alert(tet.message);
                }
            })
        },
        // 调用接口获取数据
        getBooks(){
            axios.get('books').then(ret =>{
                // console.log(ret);
                this.books=ret;
                
            });
        },
        // 格式化时间方法
        fm:function(da){
            return new Date(da).Format('yyyy-MM-dd HH:mm:ss')
        },
        // 分页加模糊查询
        findBooks:function(){
            axios.get('findBooksByName?name='+this.na+'&pageNum='+this.pageNum).then(ret => {
                // console.log(ret);
                this.books = ret.books;
                this.total = ret.total;
                this.pages = ret.pages;
            })
        },
        // 分页专用
        pag(pageNum){
            this.pageNum = pageNum;
            this.findBooks();
        }
        
    },
    directives:{
        // 自定义获取焦点
        focus:{
            inserted:function(el){
                el.focus();
            }
        }
    },
    computed:{},
    watch:{
        // 监听当name输入框的值发生改变时，判断新名字是否存在
        name(val){
            var t = this;
            axios.get('isExist?name='+val).then(ret => {
                var flage = ret.success;
                if(flage == true){
                    t.nameflag = true;
                }else{
                    t.nameflag = false;
                }
            });
        }
    },
    mounted:function(){
        // 当模板加载完成后，将后台数据加载进行数数据的初始化工作
        this.findBooks();
    }
})