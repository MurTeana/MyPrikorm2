using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using MyPrikormWebAPI.Repositories;
using MyPrikormWebAPI.Model.DB.Entities;
using NLog;

namespace MyPrikormWebAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ProductsController : ControllerBase
    {
        private ProductRepository productRepository;
        private static Logger logger = LogManager.GetCurrentClassLogger();
        public ProductsController(ProductRepository productRepository)
        {
            this.productRepository = productRepository;
        }

        [HttpGet]
        public async Task<ActionResult<List<Product>>> Get()
        {
            try
            {
                return Ok(await productRepository.GetAll());
            }
            catch (Exception ex)
            {
                logger.Error(ex);
                return BadRequest(ex);
            }
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<Product>> Get(int id)
        {
            try
            {
                Product product = await productRepository.Get(id);
                if (product == null)
                {
                    logger.Info("Product is not found: " + id + ".");
                    return NotFound();
                }

                return Ok(product);
            }
            catch (Exception ex)
            {
                logger.Error(ex);
                return BadRequest(ex);
            }
        }

        [HttpPost]
        public ActionResult<Product> Post(Product product)
        {
            if (!ModelState.IsValid)
            {
                logger.Info("Product ModelState is not valid.");
                return BadRequest();
            }

            try
            {
                return Ok(productRepository.Create(product));
            }
            catch (Exception ex)
            {
                logger.Error(ex);
                return BadRequest(ex);
            }
        }

        [HttpPut("{id}")]
        public ActionResult<Product> Put(long id, Product product)
        {
            if (!ModelState.IsValid)
            {
                logger.Info("Product ModelState is not valid.");
                return BadRequest();
            }

            try
            {
                return Ok(productRepository.Update(product));
            }
            catch (Exception ex)
            {
                logger.Error(ex);
                return BadRequest(ex);
            }
        }

        [HttpDelete("{id}")]
        public ActionResult<Product> Delete(int id)
        {
            try
            {
                Product product = productRepository.Delete(id);
                if (product == null)
                {
                    logger.Info("Product is not found: " + id + ".");
                    return NotFound();
                }

                return NoContent();
            }
            catch (Exception ex)
            {
                logger.Error(ex);
                return BadRequest(ex);
            }
        }
    }
}
