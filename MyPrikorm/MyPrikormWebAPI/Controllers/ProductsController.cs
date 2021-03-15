using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using MyPrikormWebAPI.Repositories;
using MyPrikormWebAPI.DB.Entities;

namespace MyPrikormWebAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ProductsController : ControllerBase
    {
        private ProductRepository rep;

        public ProductsController(ProductRepository rep)
        {
            this.rep = rep;
        }

        // GET api/product
        //[EnableCors("AnotherPolicy")]
        [HttpGet]
        public async Task<ActionResult<List<Product>>> Get()
        {
            return await rep.GetAll();
        }

        // GET api/values/5
        //[EnableCors("AnotherPolicy")]
        [HttpGet("{id}")]
        public async Task<ActionResult<Product>> Get(int id)
        {
            Product product = await rep.Get(id);
            if (product == null)
            {
                return NotFound();
            }
            return new ObjectResult(product);
        }

        // POST api/values
        //[EnableCors("AnotherPolicy")]
        [HttpPost]
        public async Task<ActionResult<Product>> Post(Product product)
        {
            if (product == null)
            {
                return BadRequest();
            }

            return Ok(await rep.Create(product));
        }

        // PUT api/values/5
        //[EnableCors("AnotherPolicy")]
        [HttpPut("{id}")]
        public async Task<ActionResult<Product>> Put(long id, Product product)
        {
            if (product == null)
            {
                return BadRequest();
            }

            return Ok(await rep.Update(product));
        }

        // DELETE api/values/5
        //[EnableCors("AnotherPolicy")]
        [HttpDelete("{id}")]
        public async Task<ActionResult<Product>> Delete(int id)
        {
            Product product = await rep.Delete(id);
            if (product == null)
            {
                return NotFound();
            }
            return new ObjectResult(product);
        }
    }
}
